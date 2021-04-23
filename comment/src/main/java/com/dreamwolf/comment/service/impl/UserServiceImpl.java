package com.dreamwolf.comment.service.impl;

import com.dreamwolf.comment.pojo.User;
import com.dreamwolf.comment.mapper.UserMapper;
import com.dreamwolf.comment.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
