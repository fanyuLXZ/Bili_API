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
     * 添加操作
     * @param bvid 视频id
     * @param favListID 收藏夹id
     * @return
     */
    public int inertfav(Integer bvid,Integer favListID);

    /**
     * 根据视频收藏夹id查询收藏夹下的视频数量
     * @param favListID
     * @return
     */
    public int selectfavListID(@Param("favListID") Integer favListID);

}
