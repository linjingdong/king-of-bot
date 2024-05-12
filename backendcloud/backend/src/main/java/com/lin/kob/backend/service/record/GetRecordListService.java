package com.lin.kob.backend.service.record;

import com.alibaba.fastjson2.JSONObject;

/**
 * @Description TODO
 * @Date 2024/5/8 14:43
 * @Author Lin
 * @Version 1.0
 */
public interface GetRecordListService {
    JSONObject getList(Integer page);
}
