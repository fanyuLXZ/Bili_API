package com.dreamwolf.fav.mapper;

import com.dreamwolf.fav.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户id查询用户对象
     * @param uID
     * @return
     */
//    public User selectuser(@Param("uID") Integer uID);

}
