package com.dreamwolf.watchhistory.business.mapper;

import com.dreamwolf.entity.dynamic.Userdynamic;
import com.dreamwolf.entity.watchhistory.Videohistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * 用户观看历史表 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-23
 */
@Mapper
public interface VideohistoryMapper extends BaseMapper<Videohistory> {

    /**
     * 根据视频id添加视频观看历史，如果存在就修改，不存在就添加
     * @param uid
     * @param bvid
     * @return
     */
    public int insertupdate(Integer uid, Integer bvid, LocalTime localTime);

    List<Videohistory> videohistory(@Param("uid")Integer uid,@Param("ps")Integer ps,@Param("max")String max,@Param("view_at")String view_at);
}
