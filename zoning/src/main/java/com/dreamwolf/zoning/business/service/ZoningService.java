package com.dreamwolf.zoning.business.service;

import com.dreamwolf.zoning.business.entity.Zoning;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 分区表 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
public interface ZoningService extends IService<Zoning> {
    public Map<String,Object> mapsele(String date,Integer id);
}
