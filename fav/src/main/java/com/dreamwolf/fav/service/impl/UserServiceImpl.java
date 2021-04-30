package com.dreamwolf.fav.service.impl;

import com.dreamwolf.fav.pojo.User;
import com.dreamwolf.fav.mapper.UserMapper;
import com.dreamwolf.fav.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}