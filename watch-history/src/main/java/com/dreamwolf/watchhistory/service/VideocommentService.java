package com.dreamwolf.watchhistory.service;


import com.dreamwolf.watchhistory.entities.Videocomment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideocommentService {
    public Videocomment getVideocommentbvID(@Param("bvID") int id);

    public List<Videocomment> getcxbvID(@Param("bvID") int id);
}
