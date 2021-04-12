package com.dreamwolf.member.business.service.impl;

import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.mapper.UserMapper;
import com.dreamwolf.member.business.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
