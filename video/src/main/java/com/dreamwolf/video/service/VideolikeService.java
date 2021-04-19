package com.dreamwolf.video.service;

import com.dreamwolf.video.pojo.Videolike;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
