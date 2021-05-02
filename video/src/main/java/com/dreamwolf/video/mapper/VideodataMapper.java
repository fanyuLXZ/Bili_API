package com.dreamwolf.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.video.pojo.Videodata;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 视频数据表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideodataMapper extends BaseMapper<Videodata> {


    public Videodata selectbvid(Integer bvID);

}
