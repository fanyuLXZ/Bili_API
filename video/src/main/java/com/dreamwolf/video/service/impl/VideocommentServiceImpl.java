package com.dreamwolf.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.video.mapper.VideocommentMapper;
import com.dreamwolf.video.service.VideocommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class VideocommentServiceImpl extends ServiceImpl<VideocommentMapper, Videocomment> implements VideocommentService {

    @Resource
    private VideocommentMapper videocommentMapper;

    @Override
    public List<Videocomment> selectvidercomlist(Integer[] array) {
        return videocommentMapper.selectvidercomlist(array);
    }

    @Override
    public List<Integer> selectbvidarray(Integer[] array) {
        return videocommentMapper.selectbvidarray(array);
    }

    @Override
    public List<Videocomment> selectbvID(Integer bvID) {
        return videocommentMapper.selectbvid(bvID);
    }

    @Override
    public List<Videocomment> selectlist() {
        return videocommentMapper.selectList(null);
    }
}
