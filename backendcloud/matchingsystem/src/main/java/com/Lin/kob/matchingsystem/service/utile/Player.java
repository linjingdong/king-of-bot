package com.Lin.kob.matchingsystem.service.utile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Date 2024/4/24 21:22
 * @Author Lin
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer userId;
    private Integer rating;
    private Integer waitingTime; // 等待匹配时间
}
