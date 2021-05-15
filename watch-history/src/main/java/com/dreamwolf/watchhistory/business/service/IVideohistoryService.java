package com.dreamwolf.watchhistory.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.watchhistory.Videohistory;
import com.dreamwolf.watchhistory.business.mapper.VideohistoryMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    /**
     * 根据视频id添加视频观看历史，如果存在就修改，不存在就添加
     * @param uid
     * @param bvid
     * @return
     */
    public int insertupdate(Integer uid, Integer bvid, LocalTime localTime);

    List<Videohistory> videohistory(Integer uid,Integer ps,String max,String view_at);
}
