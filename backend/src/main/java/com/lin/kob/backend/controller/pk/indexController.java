package com.lin.kob.backend.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description TODO
 * @Date 2024/2/27 10:51
 * @Author Lin
 * @Version 1.0
 */

@Controller
@RequestMapping("/pk/")
public class indexController {
    @RequestMapping("index/")
    public String index(){
        return "pk/index.html";
    }
}
