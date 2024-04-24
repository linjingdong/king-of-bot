package com.lin.kob.backend.service.impl.user.account;

import com.lin.kob.backend.pojo.User;
import com.lin.kob.backend.service.impl.utils.UserDetailsImpl;
import com.lin.kob.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/3/27 19:58
 * @Author Lin
 * @Version 1.0
 */
@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = userDetails.getUser();

        Map<String, String> map = new HashMap<>();

        map.put("error_message", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("photo", user.getPhoto());
        return map;
    }
}
