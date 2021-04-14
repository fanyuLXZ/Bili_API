package com.dreamwolf.zoning.business.service.impl;

import com.dreamwolf.zoning.business.entity.Zoning;
import com.dreamwolf.zoning.business.mapper.ZoningMapper;
import com.dreamwolf.zoning.business.service.ZoningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 分区表 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Service
public class ZoningServiceImpl extends ServiceImpl<ZoningMapper, Zoning> implements ZoningService {

    @Resource
    ZoningMapper zoningMapper;

    public Map<String,Object> mapsele(String date, Integer id){

        return zoningMapper.mapsele(date,id);
    }
}
