package com.lin.kob.backend.service.impl.pk;

import com.lin.kob.backend.consumer.WebSocketServer;
import com.lin.kob.backend.service.pk.StartGameService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Date 2024/4/24 22:40
 * @Author Lin
 * @Version 1.0
 */
@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId) {
        System.out.println("Start game:" + aId + " " + bId);

        WebSocketServer.startGame(aId, aBotId,bId,bBotId);

        return "Start game successfully!";
    }
}
