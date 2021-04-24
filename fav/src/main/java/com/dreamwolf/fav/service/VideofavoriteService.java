package com.dreamwolf.fav.service;

import com.dreamwolf.fav.pojo.Videofavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideofavoriteService {

    /**
     * 联表查，根据收藏夹id查询视频
     * @param favListID
     * @return
     */
    public List<Videofavorite> selectlist(Integer favListID);

}
