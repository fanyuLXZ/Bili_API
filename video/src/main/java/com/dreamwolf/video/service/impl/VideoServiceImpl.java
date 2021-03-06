package com.dreamwolf.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.video.mapper.VideoMapper;
import com.dreamwolf.video.service.VideoService;
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
    public Integer videoridcountselect(Integer[] array, String bvPostTime) {
        return videoMapper.videoridcountselect(array,bvPostTime);
    }

    @Override
    public Integer selectridcount(Integer rid) {
        return videoMapper.selectridcount(rid);
    }

    @Override
    public List<Video> selectlispagelsit(Integer[] array) {
        return videoMapper.selectlispagelsit(array);
    }

    @Override
    public List<Video> selectvideovlidlsit(Integer[] array, int pn, int ps) {
        return videoMapper.selectvideovlidlsit(array,pn,ps);
    }

    @Override
    public List<Integer> videoselezoingid(Integer rid) {
        return videoMapper.videoselezoingid(rid);
    }

    @Override
    public List<Video> videolistselectpage(Integer rid, Integer pn, Integer ps) {
        return videoMapper.videolistselectpage(rid,pn,ps);
    }

    @Override
    public List<Video> videoliselectridlist(Integer rid,Integer pn,Integer ps) {
        return videoMapper.videoliselectridlist(rid,pn,ps);
    }

    @Override
    public List<Video> selecvideolistpa(Integer bvid, int pagesum) {
        return videoMapper.selecvideolistpa(bvid,pagesum);
    }

    @Override
    public List<Video> selectlistBvid(Integer[] bvidlist) {
        return videoMapper.selectlistBvid(bvidlist);
    }

    @Override
    public List<Video> selectcoutbvid(String bvPostTime) {
        return videoMapper.selectcoutbvid(bvPostTime);
    }

    @Override
    public List<Video> videoPagebvzoing(Integer[] bvChildZoninglist, Integer pageSize, Integer pagecount) {
        return videoMapper.videoPagebvzoing(bvChildZoninglist,pageSize,pagecount);
    }

    @Override
    public Integer videocount(Integer[] bvChildZoninglist) {
        return videoMapper.videocount(bvChildZoninglist);
    }

    @Override
    public List<Video> selectlist() {
        return videoMapper.selectList(null);
    }

    @Override
    public List<Video> videoZoningIdlist(Integer[] bvChildZoning, String date, String datetime) {
        return videoMapper.videoZoningIdlist(bvChildZoning,date,datetime);
    }

    @Override
    public Video videobvIDlist(Integer bvID) {
        return videoMapper.videobvIDlist(bvID);
    }

    @Override
    public List<Integer> videouIDlist(Integer uID) {
        return videoMapper.videouIDlist(uID);
    }


}
