package com.lin.kob;

import com.lin.kob.backend.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = SecurityConfig.class)
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("psw1"));
        System.out.println(passwordEncoder.encode("psw2"));
        System.out.println(passwordEncoder.encode("psw2"));
        System.out.println(passwordEncoder.matches("psw2", "$2a$10$HCfvVjAYTq7eLtXLbFHR3exOqikGgsJZ9kZq6hRSFLxHvT/wkB5mO"));
    }

}
