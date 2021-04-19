package com.dreamwolf.video.service;

import com.dreamwolf.video.pojo.Videofavorite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 视频收藏表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideofavoriteService extends IService<Videofavorite> {

    /**
     * 根据被收藏的视频id(bvID)查询被收藏在那个收藏夹下
     * @param bvID
     * @return
     */
    public Videofavorite selectbvID(Integer bvID);

    /**
     * 查询视频收藏表的所有数据
     * @return
     */
    public List<Videofavorite> selectlist();


}
