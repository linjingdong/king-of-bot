package com.lin.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.kob.backend.mapper.UserMapper;
import com.lin.kob.backend.pojo.User;
import com.lin.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/3/27 22:37
 * @Author Lin
 * @Version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> Register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();

        if (username == null) {
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if (password == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        username = username.trim(); // 将用户名前后的空格，制表符等去掉
        if (username.isEmpty()) {
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if (!StringUtils.hasLength(password) || !StringUtils.hasLength(confirmedPassword)) {
            map.put("error_message", "密码长度不能为空");
            return map;
        }

        if (username.length() > 100) {
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://avatars.githubusercontent.com/u/147239169?s=400&u=f484982222e20b79fb8c485263ae17f0904c1102&v=4";

        User user = new User(null, username, encodedPassword, photo,1500);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
