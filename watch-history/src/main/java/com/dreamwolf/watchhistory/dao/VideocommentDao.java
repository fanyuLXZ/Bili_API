package com.dreamwolf.watchhistory.dao;

import com.dreamwolf.watchhistory.entities.Videocomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper
public interface VideocommentDao {
    @Autowired
    public Videocomment getVideocommentbvID(@Param("bvID") int id);
    @Autowired
    public List<Videocomment> getcxbvID(@Param("bvID") int id);
}
