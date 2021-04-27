package com.dreamwolf.zoning.business.service;

import com.dreamwolf.zoning.business.entity.Video;
import com.dreamwolf.zoning.business.entity.Zoning;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分区表 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-20
 */
public interface IZoningService extends IService<Zoning> {
    public List<Video> listInt(String riqi);

    public List<Zoning> isnull();
}
