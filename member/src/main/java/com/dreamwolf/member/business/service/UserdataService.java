package com.dreamwolf.member.business.service;

import com.dreamwolf.entity.member.Userdata;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户个人数据 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
public interface UserdataService extends IService<Userdata> {
    public Userdata select(int uID);
    boolean changeWhereExist(Userdata userdata);
    boolean coin_add(int uID);
}
