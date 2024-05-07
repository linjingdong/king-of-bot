package com.Lin.kob.matchingsystem.service.utile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Date 2024/4/24 21:22
 * @Author Lin
 * @Version 1.0
 */
@Component
public class MatchingPool extends Thread {
    /* players：将匹配的玩家存下来
    添加玩家的时候，players是多个线程共用的，线程调用添加玩家这个函数的时候，会修改players这个变量
    同时在线程内部也会用到players这个变量，会出现读写冲突的问题，所以需要用到锁
    */
    private static List<Player> players = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock(); // 定义锁
    private static RestTemplate restTemplate; // 用于微服务之间传送
    private static final String startGameUrl = "http://127.0.0.1:8080/pk/start/game/";

    @Autowired
    private void setRestTemplate(RestTemplate restTemplate) {
        MatchingPool.restTemplate = restTemplate;
    }

    // 添加玩家
    public void addPlayer(Integer userId, Integer rating, Integer botId) {
        lock.lock();
        try {
            players.add(new Player(userId, rating, botId, 0));
        } finally {
            lock.unlock();
        }
    }

    // 删除玩家，将剩下的玩家重新存下来
    public void removePlayer(Integer userId) {
        lock.lock();
        try {
            List<Player> newPlayers = new ArrayList<>();
            for (Player player : players) {
                if (!player.getUserId().equals(userId)) {
                    newPlayers.add(player);
                }
            }
            players = newPlayers;
        } finally {
            lock.unlock();
        }
    }

    // 将所有玩家的等待时间加1
    private void increaseWaitingTime() {
        for (Player player : players) {
            player.setWaitingTime(player.getWaitingTime() + 1);
        }
    }

    // 判断两名玩家是否匹配
    private boolean checkMatched(Player a, Player b) {
        int ratingDelta = Math.abs(a.getRating() - b.getRating());
        int waitingTimeMin = Math.min(a.getWaitingTime(), b.getWaitingTime());
        return ratingDelta <= waitingTimeMin * 10;
    }

    // 判断两名玩家是否匹配之后，返回匹配结果
    private void sendResult(Player a, Player b) {
        System.out.println("send result" + a + " " + b);
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("a_bot_id", a.getBotId().toString());
        data.add("b_id", b.getUserId().toString());
        data.add("b_bot_id", b.getBotId().toString());
        restTemplate.postForObject(startGameUrl, data, String.class);
    }

    // 尝试匹配所有玩家
    private void matchPlayers() {
        System.out.println("match players:" + players.toString());
        boolean[] used = new boolean[players.size()]; // 表示已经匹配过的玩家
        for (int i = 0; i < players.size(); i++) {
            if (used[i]) continue;
            for (int j = i + 1; j < players.size(); j++) {
                if (used[j]) continue;
                Player a = players.get(i), b = players.get(j);
                if (checkMatched(a, b)) {
                    used[i] = used[j] = true;
                    sendResult(a, b);
                    break;
                }
            }
        }

        List<Player> newPlayers = new ArrayList<>();
        // 将已经匹配过的玩家删除
        for (int i = 0; i < players.size(); i++) {
            if (!used[i]) {
                newPlayers.add(players.get(i));
            }
        }
        players = newPlayers;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                lock.lock();
                try {
                    increaseWaitingTime();
                    matchPlayers();
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
