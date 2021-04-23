package com.dreamwolf.comment.mapper;

import com.dreamwolf.comment.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户评论表
 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据回复的评论id查询数据，返回list
     * @param cIDreply
     * @return
     */
    public List <Comment> selectrpid(@Param("cIDreply") Integer cIDreply);

}
