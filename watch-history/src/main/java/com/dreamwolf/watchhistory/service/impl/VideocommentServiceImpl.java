package com.dreamwolf.watchhistory.service.impl;

import com.dreamwolf.watchhistory.dao.VideocommentDao;
import com.dreamwolf.watchhistory.entities.Videocomment;
import com.dreamwolf.watchhistory.service.VideocommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wzx
 * @data: 2021/4/13 10:15
 * @version: 1.0
 */
@Service
public class VideocommentServiceImpl implements VideocommentService {
    @Resource
    private VideocommentDao videocommentDao;
    @Override
    public Videocomment getVideocommentbvID(int id) {
        return videocommentDao.getVideocommentbvID(id);
    }

    @Override
    public List<Videocomment> getcxbvID(int id) {
        return videocommentDao.getcxbvID(id);
    }
}
