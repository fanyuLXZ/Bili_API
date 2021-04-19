package com.dreamwolf.video.mapper;

import com.dreamwolf.video.pojo.Videolike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频点赞表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideolikeMapper extends BaseMapper<Videolike> {

    /**
     * 根据视频id查询该视频点赞用户的id
     * @param bvID
     * @return
     */
    public List<Videolike> selectbvid(@Param("bvID") Integer bvID);

}
