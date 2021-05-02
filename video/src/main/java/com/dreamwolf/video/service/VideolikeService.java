package com.dreamwolf.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.video.pojo.Videolike;

import java.util.List;

/**
 * <p>
 * 视频点赞表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideolikeService extends IService<Videolike> {

    /**
     * 根据视频id查询视频的点赞用户id
     * @param bvid
     * @return
     */
    public List<Videolike> selectbvid(Integer bvid);

    /**
     * 查询视频点赞表的所有数据
     * @return
     */
    public List<Videolike> selectlist();


    /**
     * 根据bvid数组查询所有的视频id的点赞表数据
     * @param bvidlist
     * @return
     */
    public List<Videolike> selectbvidlist(Integer[] bvidlist);

}
