package com.Lin.kob.botrunningsystem.utils;

/**
 * @Description 用户执行一个操作的时候，实现的是这个API
 * @Date 2024/5/6 10:52
 * @Author Lin
 * @Version 1.0
 */
public interface BotInterface {
    Integer nextMove(String input); // 下一步的方向是什么
}
