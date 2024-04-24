package com.lin.kob.backend.controller.user.account;

import com.lin.kob.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/3/27 21:59
 * @Author Lin
 * @Version 1.0
 */

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("user/account/info/")
    public Map<String, String> getInfo(){
        return infoService.getInfo();
    }
}
