package com.lin.kob.backend.controller.user.bot;

import com.lin.kob.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/4/7 12:58
 * @Author Lin
 * @Version 1.0
 */

@RestController
public class UpdateController {
    @Autowired
    private UpdateService updateService;

    @PostMapping("/user/bot/update/")
    public Map<String, String> update(@RequestBody Map<String, String> data){
        return updateService.update(data);
    }
}
