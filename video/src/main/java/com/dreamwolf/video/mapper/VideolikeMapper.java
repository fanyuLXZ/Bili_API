package com.dreamwolf.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.video.Videolike;
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
     * 判断数据存不存在，存在就修改，不存在就添加
     * @param bvID 视频id
     * @param uID 用户id
     * @return
     */
    public int updateinset(Integer bvID,Integer uID);



    /**
     * 根据视频id查询该视频点赞用户的id
     * @param bvID
     * @return
     */
    public List<Videolike> selectbvid(@Param("bvID") Integer bvID);

    /**
     * 根据bvid数组查询所有的视频id的点赞表数据
     * @param bvidlist
     * @return
     */
    public List<Videolike> selectbvidlist(Integer[] bvidlist);

}
