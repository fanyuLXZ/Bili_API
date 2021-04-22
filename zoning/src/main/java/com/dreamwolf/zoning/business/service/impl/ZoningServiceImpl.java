package com.dreamwolf.zoning.business.service.impl;

import com.dreamwolf.zoning.business.entity.Video;
import com.dreamwolf.zoning.business.entity.Zoning;
import com.dreamwolf.zoning.business.mapper.ZoningMapper;
import com.dreamwolf.zoning.business.service.IZoningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分区表 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-20
 */
@Service
public class ZoningServiceImpl extends ServiceImpl<ZoningMapper, Zoning> implements IZoningService {
    @Resource
    ZoningMapper zoningMapper;

    @Override
    public List<Video> listInt(String riqi) {
        return zoningMapper.listInt(riqi);
    }

    @Override
    public List<Zoning> isnull() {
        return zoningMapper.isnull();
    }
}
