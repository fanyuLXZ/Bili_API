package com.dreamwolf.zoning.business.service;

import com.dreamwolf.entity.zoning.web_interface.Video;
import com.dreamwolf.entity.zoning.Zoning;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
