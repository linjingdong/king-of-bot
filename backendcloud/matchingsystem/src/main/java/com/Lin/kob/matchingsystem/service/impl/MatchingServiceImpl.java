package com.Lin.kob.matchingsystem.service.impl;

import com.Lin.kob.matchingsystem.service.MatchingService;
import com.Lin.kob.matchingsystem.service.utile.MatchingPool;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Date 2024/4/24 15:46
 * @Author Lin
 * @Version 1.0
 */
@Service
public class MatchingServiceImpl implements MatchingService {

    public static final MatchingPool matchingPool = new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating, Integer botId) {
        matchingPool.addPlayer(userId, rating, botId);

        System.out.println("add player" + userId + "-" + rating);
        return "Add player successfully!";
    }

    @Override
    public String removePlayer(Integer userId) {
        matchingPool.removePlayer(userId);

        System.out.println("remove player" + userId);
        return "Remove player successfully!";
    }
}
