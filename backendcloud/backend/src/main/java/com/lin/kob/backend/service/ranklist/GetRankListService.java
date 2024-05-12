package com.lin.kob.backend.service.ranklist;

import com.alibaba.fastjson2.JSONObject;

/**
 * @Description TODO
 * @Date 2024/5/12 21:48
 * @Author Lin
 * @Version 1.0
 */
public interface GetRankListService {
    JSONObject getList(Integer page);
}
