package com.dreamwolf.member.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.entity.member.Vip;

import com.dreamwolf.member.business.mapper.VipMapper;
import com.dreamwolf.member.business.service.VipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户大会员信息 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip> implements VipService {
    @Resource
    VipMapper vipMapper;

    @Override
    public Vip vipselect(int uID) {
        return vipMapper.vipselect(uID);
    }
}
