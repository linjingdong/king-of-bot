package com.lin.kob.backend.service.impl.user.bot;

import com.lin.kob.backend.mapper.BotMapper;
import com.lin.kob.backend.pojo.Bot;
import com.lin.kob.backend.pojo.User;
import com.lin.kob.backend.service.impl.utils.UserDetailsImpl;
import com.lin.kob.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/4/7 12:45
 * @Author Lin
 * @Version 1.0
 */
@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int botId = Integer.parseInt(data.get("bot_id"));

        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        Map<String, String> map = new HashMap<>();

        if (title == null || title.isEmpty()) {
            map.put("error_message", "标题不能为空");
            return map;
        }

        if (title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }


        if (description == null) {
            description = "这个用户很懒，什么也没留下~";
        }

        if (description.length() > 300) {
            map.put("error_message", "Bot描述长度不能大于300");
            return map;
        }

        if (content == null || content.isEmpty()) {
            map.put("error_message", "代码长度不能为空");
            return map;
        }

        if (content.length() > 10000) {
            map.put("error_message", "代码长度不能大于10000");
            return map;
        }

        Bot bot = botMapper.selectById(botId);

        if (bot == null) {
            map.put("error_message", "Bot不存在或者已被删除");
            return map;
        }

        if (!bot.getUserId().equals(user.getId())) {
            map.put("error_message", "该Bot无权限修改");
            return map;
        }

        Bot newBot = new Bot(bot.getId(), user.getId(), title, description, content, bot.getRating(), bot.getCreateTime(), new Date());

        botMapper.updateById(newBot);

        map.put("error_message", "success");

        return map;
    }
}
