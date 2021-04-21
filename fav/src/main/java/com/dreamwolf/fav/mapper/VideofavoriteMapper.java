package com.dreamwolf.fav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.fav.pojo.Videofavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface VideofavoriteMapper extends BaseMapper<Videofavorite> {


    /**
     * 联表查，根据收藏夹id查询视频
     * @param favListID
     * @return
     */
    public List<Videofavorite> selectlist(@Param("favListID") Integer favListID);


}
