package com.dreamwolf.watchhistory.business.service.impl;

import com.dreamwolf.entity.watchhistory.Videohistory;
import com.dreamwolf.watchhistory.business.mapper.VideohistoryMapper;
import com.dreamwolf.watchhistory.business.service.IVideohistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * 用户观看历史表 服务实现类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-23
 */
@Service
public class VideohistoryServiceImpl extends ServiceImpl<VideohistoryMapper, Videohistory> implements IVideohistoryService {

    @Resource
    private VideohistoryMapper videohistoryMapper;

    @Override
    public int insertupdate(Integer uid, Integer bvid, LocalTime localTime) {
        return videohistoryMapper.insertupdate(uid,bvid,localTime);
    }

    @Override
    public List<Videohistory> videohistory(Integer uid, Integer ps, String max, String view_at) {
        return videohistoryMapper.videohistory(uid,ps,max,view_at);
    }
}
