package com.lin.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/2/27 12:59
 * @Author Lin
 * @Version 1.0
 */
@RestController
@RequestMapping("/pk/")
public class BotInfoController {

    @RequestMapping("getBotInfo/")
    public Map<String, String> getBotInfoList() {
        Map<String, String> map = new HashMap<>();

        map.put("name", "tiger");
        map.put("rating","1900");

        return map;
    }
}
