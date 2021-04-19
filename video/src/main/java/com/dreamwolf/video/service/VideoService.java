package com.dreamwolf.video.service;

import com.dreamwolf.video.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频基础信息表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideoService extends IService<Video> {

    /**
     * 查video的所有数据
     * @return
     */
    public List<Video> selectlist();

    /**
     * 通过子分区id查视频,返回list
     * @param bvChildZoning
     * @return
     */
    public List<Video> videoZoningIdlist(Integer bvChildZoning);

    /**
     * 通过bv号查视频，返回List
     * @param bvID
     * @return
     */
    public Video videobvIDlist(Integer bvID);

    /**
     * 通过作者id查视频，返回list
     * @param uID
     * @return
     */
    public List<Video> videouIDlist(Integer uID);

}
