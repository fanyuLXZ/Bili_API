package com.dreamwolf.video.mapper;

import com.dreamwolf.video.pojo.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频基础信息表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 通过子分区id查视频,返回list
     * @param bvChildZoning
     * @return
     */
    public List<Video> videoZoningIdlist(@Param("bvChildZoning") Integer bvChildZoning);

    /**
     * 通过bv号查视频，返回对象
     * @param bvID
     * @return
     */
    public Video videobvIDlist(@Param("bvID") Integer bvID);

    /**
     * 通过作者id查视频，返回list
     * @param uID
     * @return
     */
    public List<Video> videouIDlist(@Param("uID") Integer uID);



}
