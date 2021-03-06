package com.dreamwolf.dynamic.business.service.impl;

import com.dreamwolf.entity.dynamic.Dynamiclike;
import com.dreamwolf.dynamic.business.mapper.DynamiclikeMapper;
import com.dreamwolf.dynamic.business.service.DynamiclikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态点赞表，用于区分用户点赞 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class DynamiclikeServiceImpl extends ServiceImpl<DynamiclikeMapper, Dynamiclike> implements DynamiclikeService {

}
