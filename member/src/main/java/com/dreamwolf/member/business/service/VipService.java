package com.dreamwolf.member.business.service;

import com.dreamwolf.entity.member.Vip;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户大会员信息 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
public interface VipService extends IService<Vip> {
    public Vip vipselect(@Param("uID")int uID);
}
