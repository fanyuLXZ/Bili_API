package com.dreamwolf.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.video.Video;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 视频基础信息表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

}
