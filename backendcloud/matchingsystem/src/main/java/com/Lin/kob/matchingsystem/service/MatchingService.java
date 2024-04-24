package com.Lin.kob.matchingsystem.service;

/**
 * @Description TODO
 * @Date 2024/4/24 15:44
 * @Author Lin
 * @Version 1.0
 */

public interface MatchingService {
    String addPlayer(Integer userId, Integer rating);
    String removePlayer(Integer userId);
}
