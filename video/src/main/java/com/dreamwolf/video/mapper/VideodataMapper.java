package com.dreamwolf.video.mapper;

import com.dreamwolf.video.pojo.Videodata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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


    /**
     * 根据视频id查询视频数据
     * @param bvID 视频id
     * @return
     */
    public Videodata selectbvid(Integer bvID);

}
