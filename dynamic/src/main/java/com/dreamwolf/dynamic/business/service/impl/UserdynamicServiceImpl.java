package com.dreamwolf.dynamic.business.service.impl;

import com.dreamwolf.entity.dynamic.Userdynamic;
import com.dreamwolf.dynamic.business.mapper.UserdynamicMapper;
import com.dreamwolf.dynamic.business.service.UserdynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户动态表 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class UserdynamicServiceImpl extends ServiceImpl<UserdynamicMapper, Userdynamic> implements UserdynamicService {
    @Resource
    UserdynamicMapper userdynamicMapper;

    @Override
    public List<Userdynamic> listmap(Integer udID,Integer[] shuzu) {
        return userdynamicMapper.listmap(udID,shuzu);
    }
}
