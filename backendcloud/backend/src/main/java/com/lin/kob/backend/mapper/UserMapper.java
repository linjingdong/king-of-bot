package com.lin.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.kob.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description TODO
 * @Date 2024/3/16 20:59
 * @Author Lin
 * @Version 1.0
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
