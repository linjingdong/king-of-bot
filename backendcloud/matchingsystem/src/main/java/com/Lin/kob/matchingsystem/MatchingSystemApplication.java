package com.Lin.kob.matchingsystem;

import com.Lin.kob.matchingsystem.service.impl.MatchingServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TODO
 * @Date 2024/4/23 22:16
 * @Author Lin
 * @Version 1.0
 */
@SpringBootApplication
public class MatchingSystemApplication {
    public static void main(String[] args) {
        MatchingServiceImpl.matchingPool.start(); // 启动匹配线程
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}