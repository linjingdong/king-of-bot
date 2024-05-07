package com.Lin.kob.botrunningsystem.service.impl;

import com.Lin.kob.botrunningsystem.service.BotRunningService;
import com.Lin.kob.botrunningsystem.service.impl.utils.BotPool;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Date 2024/5/4 22:27
 * @Author Lin
 * @Version 1.0
 */
@Service
public class BotRunningServiceImpl implements BotRunningService {
    public static final BotPool botPool = new BotPool();

    @Override
    public String addBot(Integer userId, String botCode, String input) {
        botPool.addBot(userId, botCode, input);
        return "add bot success";
    }
}
