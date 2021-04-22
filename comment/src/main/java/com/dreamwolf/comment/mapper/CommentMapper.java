package com.dreamwolf.comment.mapper;

import com.dreamwolf.comment.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
