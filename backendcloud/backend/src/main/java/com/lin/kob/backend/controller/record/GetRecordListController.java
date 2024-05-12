package com.lin.kob.backend.controller.record;

import com.alibaba.fastjson2.JSONObject;
import com.lin.kob.backend.service.record.GetRecordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/5/8 14:47
 * @Author Lin
 * @Version 1.0
 */
@RestController
public class GetRecordListController {
    @Autowired
    private GetRecordListService getRecordListService;

    @GetMapping("/record/getlist/{page}")
    public JSONObject getRecordList(@PathVariable Integer page) {
        return getRecordListService.getList(page);
    }
}
