package com.dreamwolf.watchhistory.service.impl;

import com.dreamwolf.watchhistory.dao.VideoDao;
import com.dreamwolf.watchhistory.entities.Video;
import com.dreamwolf.watchhistory.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wzx
 * @data: 2021/4/13 10:15
 * @version: 1.0
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoDao videoDao;
    @Override
    public Video getVideobvID(int id) {
        return videoDao.getVideobvID(id);
    }

    @Override
    public List<Video> getcxunbvID(int id) {
        return videoDao.getcxunbvID(id);
    }
}
