package com.dreamwolf.comment.mapper;

import com.dreamwolf.comment.pojo.Commentdata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 评论数据表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Mapper
public interface CommentdataMapper extends BaseMapper<Commentdata> {

    /**
     * 根据评论id查询点赞数和点踩数
     * @param cID
     * @return
     */
    public Commentdata selectcID(@Param("cID") Integer cID);

}
