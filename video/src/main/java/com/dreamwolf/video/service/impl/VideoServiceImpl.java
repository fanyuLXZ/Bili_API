package com.dreamwolf.video.service.impl;

import com.dreamwolf.video.pojo.Video;
import com.dreamwolf.video.mapper.VideoMapper;
import com.dreamwolf.video.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 视频基础信息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private VideoMapper videoMapper;


    @Override
    public List<Video> selectlist() {
        return videoMapper.selectList(null);
    }

    @Override
    public List<Video> videoZoningIdlist(Integer bvChildZoning) {
        return videoMapper.videoZoningIdlist(bvChildZoning);
    }

    @Override
    public Video videobvIDlist(Integer bvID) {
        return videoMapper.videobvIDlist(bvID);
    }

    @Override
    public List<Video> videouIDlist(Integer uID) {
        return videoMapper.videouIDlist(uID);
    }
}
