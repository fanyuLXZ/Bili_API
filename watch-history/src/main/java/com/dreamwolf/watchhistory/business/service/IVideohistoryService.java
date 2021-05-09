package com.dreamwolf.watchhistory.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.watchhistory.Videohistory;
import com.dreamwolf.watchhistory.business.mapper.VideohistoryMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户观看历史表 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-23
 */
public interface IVideohistoryService extends IService<Videohistory> {
    List<Videohistory> videohistory(Integer uid,Integer ps,String max,String view_at);
}
