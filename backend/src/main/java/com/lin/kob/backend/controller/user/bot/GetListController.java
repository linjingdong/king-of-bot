package com.lin.kob.backend.controller.user.bot;

import com.lin.kob.backend.pojo.Bot;
import com.lin.kob.backend.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Date 2024/4/7 19:40
 * @Author Lin
 * @Version 1.0
 */

@RestController
public class GetListController {
    @Autowired
    private GetListService getListService;

    @GetMapping("/user/bot/getList/")
    public List<Bot> getList() {
        return getListService.getList();
    }
}
