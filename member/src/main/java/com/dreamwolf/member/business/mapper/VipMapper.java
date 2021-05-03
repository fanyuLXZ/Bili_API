package com.dreamwolf.member.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.member.Vip;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户大会员信息 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Mapper
public interface VipMapper extends BaseMapper<Vip> {
    public Vip vipselect(@Param("uID")int uID);
}
