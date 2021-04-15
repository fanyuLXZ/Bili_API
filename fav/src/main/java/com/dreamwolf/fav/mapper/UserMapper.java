package com.dreamwolf.fav.mapper;

import com.dreamwolf.fav.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
