package com.dreamwolf.member.business.service;

import com.dreamwolf.member.business.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
public interface UserService extends IService<User> {
    public User select(String userName);
}
