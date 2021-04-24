package com.dreamwolf.comment.service;

import com.dreamwolf.comment.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户评论表
 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
public interface CommentService extends IService<Comment> {

    /**
     * 根据回复的评论id查询数据，返回list
     * @param cIDreply
     * @return
     */
    public List<Comment> selectrpid(Integer cIDreply);

}
