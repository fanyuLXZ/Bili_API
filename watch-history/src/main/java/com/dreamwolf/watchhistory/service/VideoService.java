package com.dreamwolf.watchhistory.service;

import com.dreamwolf.watchhistory.entities.Video;
import com.dreamwolf.watchhistory.entities.Videocomment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoService {
    public Video getVideobvID(@Param("bvID") int id);
    public List<Video> getcxunbvID(@Param("bvID") int id);
}
