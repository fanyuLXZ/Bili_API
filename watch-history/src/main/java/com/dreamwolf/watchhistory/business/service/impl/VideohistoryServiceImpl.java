package com.dreamwolf.watchhistory.business.service.impl;

import com.dreamwolf.entity.watchhistory.Videohistory;
import com.dreamwolf.watchhistory.business.mapper.VideohistoryMapper;
import com.dreamwolf.watchhistory.business.service.IVideohistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
