package com.dreamwolf.zoning.business.mapper;

import com.dreamwolf.entity.zoning.web_interface.Video;
import com.dreamwolf.entity.zoning.Zoning;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 分区表 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-20
 */
@Mapper
public interface ZoningMapper extends BaseMapper<Zoning> {
    public List<Video> listInt(@Param("riqi")String riqi);

    public List<Zoning> isnull();

}
