package com.dreamwolf.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.video.pojo.Videolike;
import com.dreamwolf.video.mapper.VideolikeMapper;
import com.dreamwolf.video.service.VideolikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 视频点赞表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class VideolikeServiceImpl extends ServiceImpl<VideolikeMapper, Videolike> implements VideolikeService {

    @Resource
    private VideolikeMapper videolikeMapper;

    @Override
    public List<Videolike> selectbvid(Integer bvid) {
        return videolikeMapper.selectbvid(bvid);
    }

    @Override
    public List<Videolike> selectlist() {
        return videolikeMapper.selectList(null);
    }

    @Override
    public List<Videolike> selectbvidlist(Integer[] bvidlist) {
        return videolikeMapper.selectbvidlist(bvidlist);
    }
}
