package com.lin.kob.backend.controller.ranklist;

import com.alibaba.fastjson2.JSONObject;
import com.lin.kob.backend.service.ranklist.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Date 2024/5/12 21:49
 * @Author Lin
 * @Version 1.0
 */
@RestController
public class GetRankListController {
    @Autowired
    private GetRankListService getRankListService;

    @GetMapping("/ranklist/getlist/{page}")
    public JSONObject getList(@PathVariable Integer page){
        return getRankListService.getList(page);
    }
}
