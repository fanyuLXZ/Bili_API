package com.dreamwolf.video.mapper;

import com.dreamwolf.video.pojo.Videocomment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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


}
