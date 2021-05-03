package com.dreamwolf.fav.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.fav.mapper.VideoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频基础信息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-27
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IService<Video> {

}
