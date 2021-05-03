package com.dreamwolf.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.entity.video.Videorating;
import com.dreamwolf.video.mapper.VideoratingMapper;
import com.dreamwolf.video.service.VideoratingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 视频综合评分表，根据该表判断视频排名 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class VideoratingServiceImpl extends ServiceImpl<VideoratingMapper, Videorating> implements VideoratingService {

    @Resource
    private VideoratingMapper videoratingMapper;

    @Override
    public List<Videorating> selectvideolist() {
        return videoratingMapper.selectvideolist();
    }

    @Override
    public Videorating selectbvid(Integer bvID) {
        return videoratingMapper.selectbvid(bvID);
    }

    @Override
    public List<Videorating> selectlist() {
        return videoratingMapper.selectList(null);
    }
}
