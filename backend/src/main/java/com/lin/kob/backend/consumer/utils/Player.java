package com.lin.kob.backend.consumer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Date 2024/4/17 15:05
 * @Author Lin
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer id;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;

    private boolean check_tail_increasing(int step) { // 检验当前回合蛇的长度是否增加
        if (step <= 10) return true;
        return step % 3 == 1;
    }


    public List<Cell> getCells() {
        List<Cell> res = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for (int d : steps) {
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            if (!check_tail_increasing(++step)) {
                res.remove(0);
            }
        }
        return res;
    }

    public String getStepString() {
        StringBuilder res = new StringBuilder();
        for (int step : steps) {
            res.append(step);
        }

        return res.toString();
    }
}
