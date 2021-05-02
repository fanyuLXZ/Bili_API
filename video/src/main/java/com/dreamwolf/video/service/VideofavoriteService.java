package com.dreamwolf.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.video.pojo.Videofavorite;

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


    /**
     * 根据视频收藏夹id查询收藏夹下的视频数量
     * @param favListID
     * @return
     */
    public int selectfavListID(Integer favListID);


}
