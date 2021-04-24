package com.dreamwolf.comment.service.impl;

import com.dreamwolf.comment.pojo.Video;
import com.dreamwolf.comment.mapper.VideoMapper;
import com.dreamwolf.comment.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频基础信息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
