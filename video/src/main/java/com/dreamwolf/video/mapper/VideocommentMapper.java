package com.dreamwolf.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.video.Videocomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideocommentMapper extends BaseMapper<Videocomment> {

    public List<Videocomment> selectbvid(@Param("bvID") Integer bvID);

    /**
     * 根据视频id数组查询视频评论id 返回cidlist
     * @param array
     * @return
     */
    public List<Integer> selectbvidarray(Integer[] array);

    /**
     *  根据视频id数组查询视频评论表数据
     * @param array
     * @return
     */
    public List<Videocomment> selectvidercomlist(Integer[] array);


}
