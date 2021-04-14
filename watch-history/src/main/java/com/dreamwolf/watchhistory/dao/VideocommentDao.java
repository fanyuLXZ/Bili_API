package com.dreamwolf.watchhistory.dao;

import com.dreamwolf.watchhistory.entities.Videocomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VideocommentDao {
    public Videocomment getVideocommentbvID(@Param("bvID") int id);
    public List<Videocomment> getcxbvID(@Param("bvID") int id);
}
