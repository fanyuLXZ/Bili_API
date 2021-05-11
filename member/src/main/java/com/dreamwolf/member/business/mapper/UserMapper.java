package com.dreamwolf.member.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.member.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public User select(@Param("userName")String userName);
    public Integer insertToUser(User user);
}
