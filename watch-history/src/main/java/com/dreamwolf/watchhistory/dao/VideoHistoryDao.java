package com.dreamwolf.watchhistory.dao;
//用户观看历史表

import com.dreamwolf.watchhistory.entities.VideoHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface VideoHistoryDao {
     @Autowired
     VideoHistory getCxqb(@Param("bvID") int id);

}
