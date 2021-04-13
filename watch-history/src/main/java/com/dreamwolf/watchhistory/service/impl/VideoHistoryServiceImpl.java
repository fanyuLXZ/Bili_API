package com.dreamwolf.watchhistory.service.impl;

///**
// * @author: wzx
// * @data: 2021/4/7 10:04
// * @version: 1.0
// */
//用户观看历史表

import com.dreamwolf.watchhistory.dao.VideoHistoryDao;
import com.dreamwolf.watchhistory.entities.VideoHistory;
import com.dreamwolf.watchhistory.service.VideoHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VideoHistoryServiceImpl implements VideoHistoryService {
    @Resource
    private VideoHistoryDao videoHistoryDao;

    @Override
    public VideoHistory getCxqb(int id) {
        return videoHistoryDao.getCxqb(id);
    }
}
