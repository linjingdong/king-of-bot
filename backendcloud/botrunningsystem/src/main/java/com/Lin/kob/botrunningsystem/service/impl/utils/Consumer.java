package com.Lin.kob.botrunningsystem.service.impl.utils;

import com.Lin.kob.botrunningsystem.utils.BotInterface;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @Description 新开一个线程去执行bot的代码，因为bot代码可能会有死循环，所以在规定最多执行timeout秒
 * @Date 2024/5/6 10:48
 * @Author Lin
 * @Version 1.0
 */
@Component
public class Consumer extends Thread {
    private Bot bot;
    private static RestTemplate restTemplate;
    private static final String receiveBotMoveUrl = "http://127.0.0.1:8080/pk/receive/bot/move/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Consumer.restTemplate = restTemplate;
    }

    public void startTimeout(long timeout, Bot bot) {
        this.bot = bot;
        this.start(); // 开一个线程去执行run();

        try {
            this.join(timeout); // 最多等待timeout秒;和sleep的区别是：sleep必须执行timeout秒，而join最多执行timeout秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.interrupt(); // 中断当前线程
        }
    }

    private String addUid(String code, String uid) { // 在bot类名后面加上uid
        int k = code.indexOf(" implements com.Lin.kob.botrunningsystem.utils.BotInterface");
        return code.substring(0, k) + uid + code.substring(k);
    }

    @Override
    public void run() {
        /*
         * 类重名的时候只会编译一次，但是程序一直在运行的同时接收到用户的新的服务
         * 每一次输入应该去重新编译一遍，因为每个用户的代码不一定一样
         * 为了能让所有的类不一样，在类名后面加个随机字符串
         * */
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString().substring(0, 8);

        BotInterface botInterface = Reflect.compile(
                "com.Lin.kob.botrunningsystem.utils.Bot" + uid,
                addUid(bot.getBotCode(), uid)
        ).create().get();

        Integer direction = botInterface.nextMove(bot.getInput());

        System.out.println("move-direction:" + bot.getUserId() + " " + direction);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", bot.getUserId().toString());
        data.add("direction", direction.toString());

        restTemplate.postForObject(receiveBotMoveUrl, data, String.class);
    }
}
