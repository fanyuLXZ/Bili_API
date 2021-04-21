package com.dreamwolf.watchhistory.dao;

import com.dreamwolf.watchhistory.entities.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper
public interface VideoDao {
    @Autowired
    public Video getVideobvID(@Param("bvID") int id);
    @Autowired
    public List<Video> getcxunbvID(@Param("bvID") int id);

}
