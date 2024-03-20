package com.lin.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.kob.backend.mapper.UserMapper;
import com.lin.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Date 2024/3/16 21:32
 * @Author Lin
 * @Version 1.0
 */

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/all")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @GetMapping("/user/{userid}")
    public User getUserById(@PathVariable("userid") int userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); // 条件构造器
        queryWrapper.eq("id", userid);
        return userMapper.selectById(userid);
    }

    @GetMapping("/user/add/{userid}/{username}/{password}")
    public String addUser(@PathVariable("userid") int userid, @PathVariable("username") String username, @PathVariable("password") String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(userid, username, encodePassword);
        userMapper.insert(user);
        return "Add User Successfully!";
    }

    @GetMapping("/user/delete/{userid}")
    public String deleteUserById(@PathVariable("userid") int userid) {
        userMapper.deleteById(userid);
        return "Delete User Successfully!";
    }
}
