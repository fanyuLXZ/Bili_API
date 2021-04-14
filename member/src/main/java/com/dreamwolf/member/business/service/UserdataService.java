package com.dreamwolf.member.business.service;

import com.dreamwolf.member.business.entity.Userdata;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

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
}
