package com.dreamwolf.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.entity.video.Videodata;
import com.dreamwolf.video.mapper.VideodataMapper;
import com.dreamwolf.video.service.VideodataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 视频数据表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class VideodataServiceImpl extends ServiceImpl<VideodataMapper, Videodata> implements VideodataService {

    @Resource
    private VideodataMapper videodataMapper;

    @Override
    public List<Videodata> videodatabvid(Integer[] array) {
        return videodataMapper.videodatabvid(array);
    }

    @Override
    public Videodata selectbvID(Integer bvID) {
        return videodataMapper.selectbvid(bvID);
    }

    @Override
    public List<Videodata> selectlist() {
        return videodataMapper.selectList(null);
    }
}
