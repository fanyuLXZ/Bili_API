package com.dreamwolf.video.service.impl;

import com.dreamwolf.video.pojo.User;
import com.dreamwolf.video.mapper.UserMapper;
import com.dreamwolf.video.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
