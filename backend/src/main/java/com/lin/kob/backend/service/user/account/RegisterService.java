package com.lin.kob.backend.service.user.account;

import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/3/21 16:47
 * @Author Lin
 * @Version 1.0
 */
public interface RegisterService {
    Map<String, String> Register(String username, String password, String confirmedPassword);
}
