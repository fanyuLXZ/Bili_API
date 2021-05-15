package com.dreamwolf.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.video.Videofavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 视频收藏表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideofavoriteMapper extends BaseMapper<Videofavorite> {

    /**
     * 根据视频收藏夹id查询收藏夹下的视频数量
     * @param favListID
     * @return
     */
    public int selectfavListID(@Param("favListID") Integer favListID);

    /**
     * 根据视频id查询视频收藏夹
     * @param bvid
     * @return
     */
    public Videofavorite selectfavbvid(Integer bvid);

}
