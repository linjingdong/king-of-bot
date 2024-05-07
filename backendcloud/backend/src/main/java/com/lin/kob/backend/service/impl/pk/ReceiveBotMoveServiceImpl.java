package com.lin.kob.backend.service.impl.pk;

import com.lin.kob.backend.consumer.WebSocketServer;
import com.lin.kob.backend.consumer.utils.Game;
import com.lin.kob.backend.service.pk.ReceiveBotMoveService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Date 2024/5/7 11:05
 * @Author Lin
 * @Version 1.0
 */
@Service
public class ReceiveBotMoveServiceImpl implements ReceiveBotMoveService {
    @Override
    public String ReceiveBotMove(Integer userId, Integer direction) {
        System.out.println("receive bot move:" + userId + " " + direction + " ");
        if (WebSocketServer.users.get(userId) != null) {
            Game game = WebSocketServer.users.get(userId).game;
            if (game != null) {
                if (game.getPlayerA().getId().equals(userId)) {
                    game.setNextStepA(direction);
                } else if (game.getPlayerB().getId().equals(userId)) {
                    game.setNextStepB(direction);
                }
            }
        }
        return "receive bot move success";
    }
}
