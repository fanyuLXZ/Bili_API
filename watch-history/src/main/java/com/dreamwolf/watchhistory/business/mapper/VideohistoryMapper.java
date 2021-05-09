package com.dreamwolf.watchhistory.business.mapper;

import com.dreamwolf.entity.dynamic.Userdynamic;
import com.dreamwolf.entity.watchhistory.Videohistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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
    List<Videohistory> videohistory(@Param("uid")Integer uid,@Param("ps")Integer ps,@Param("max")String max,@Param("view_at")String view_at);
}
