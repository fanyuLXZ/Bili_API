package com.dreamwolf.watchhistory.dao;
//用户观看历史表

import com.dreamwolf.watchhistory.entities.VideoHistory;
import org.apache.ibatis.annotations.Param;

public interface VideoHistoryDao {

    public VideoHistory getCxqb(@Param("bvID") int id);

}
