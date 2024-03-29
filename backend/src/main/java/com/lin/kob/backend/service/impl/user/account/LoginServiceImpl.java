package com.lin.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.kob.backend.mapper.UserMapper;
import com.lin.kob.backend.pojo.User;
import com.lin.kob.backend.service.impl.utils.UserDetailsImpl;
import com.lin.kob.backend.service.user.account.LoginService;
import com.lin.kob.backend.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/3/21 16:58
 * @Author Lin
 * @Version 1.0
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken); // 登录失败，会自动处理

        UserDetailsImpl LoginUser = (UserDetailsImpl) authenticate.getPrincipal();

        User user = LoginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString());

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("token",jwt);

        return map;
    }
}
