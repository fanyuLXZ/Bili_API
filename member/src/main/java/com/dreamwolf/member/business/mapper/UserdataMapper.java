package com.dreamwolf.member.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.member.Userdata;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户个人数据 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Mapper
public interface UserdataMapper extends BaseMapper<Userdata> {
    public Userdata select(@Param("uID")int uID);
}
