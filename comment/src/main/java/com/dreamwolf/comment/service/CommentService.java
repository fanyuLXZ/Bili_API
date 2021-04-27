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
     * 通过cid评论主键查询数据
     * @param cid
     * @return
     */
    public List<Comment> commselectlistcid(Integer cid);

    /**
     *  查询当前用户uid发布的评论
     * @param uID
     * @return
     */
    public List<Comment> commselectlistuid(Integer uID);


    /**
     * 根据评论id查询评论数据
     * @return
     */
    public Comment commcidlist(Integer cID);


    /**
     * 根据回复的评论id查询数据，返回list
     * @param cIDreply
     * @return
     */
    public List<Comment> selectrpid(Integer cIDreply);

    /**
     * 根据评论id返回评论总数
     * @param cid
     * @return
     */
    public Integer selectbvidint(Integer[] cid);


    /**
     * 根据当前的用户id查询用户下的评论
     * @param uID
     * @return
     */
    public List<Integer> selectuidlist(Integer uID);

    /**
     * 根据评论id批量查询用户评论
     * @param array
     * @return
     */
    public List<Comment> commentuidlist(Integer[] array);

}
