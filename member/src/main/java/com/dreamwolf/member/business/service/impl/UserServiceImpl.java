package com.dreamwolf.member.business.service.impl;

import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.mapper.UserMapper;
import com.dreamwolf.member.business.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User select(String userName) {
        return userMapper.select(userName);
    }
}
