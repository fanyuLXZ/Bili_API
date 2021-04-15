package com.dreamwolf.watchhistory.dao;

import com.dreamwolf.watchhistory.entities.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VideoDao {
    public Video getVideobvID(@Param("bvID") int id);
    public List<Video> getcxunbvID(@Param("bvID") int id);

}
