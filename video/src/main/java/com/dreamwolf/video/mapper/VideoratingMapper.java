package com.dreamwolf.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.video.pojo.Videorating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 视频综合评分表，根据该表判断视频排名 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideoratingMapper extends BaseMapper<Videorating> {

    /**
     * 根据视频id(bvID)查询视频评分
     * @param bvID
     * @return
     */
    public Videorating selectbvid(@Param("bvID") Integer bvID);

    /**
     * 查询所有视频评分根据视频评分排序
     * @return
     */
    public List<Videorating> selectvideolist();



}
