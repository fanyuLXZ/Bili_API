package com.dreamwolf.member.business.service.impl;

import com.dreamwolf.member.business.entity.Userdata;
import com.dreamwolf.member.business.mapper.UserdataMapper;
import com.dreamwolf.member.business.service.IUserdataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户个人数据 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class UserdataServiceImpl extends ServiceImpl<UserdataMapper, Userdata> implements IUserdataService {

    @Resource
    UserdataMapper userdataMapper;

    @Override
    public Userdata select(int uID) {
        return userdataMapper.select(uID);
    }
}
