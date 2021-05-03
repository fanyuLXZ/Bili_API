package com.dreamwolf.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.comment.Comment;

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
     * 根据评论id数组去查询数据 并且按照最新时间排序 并分页处理
     * @param array
     * next 页码
     * @return
     */
    public List<Comment> comdatalisttimepage(Integer[] array,Integer next);


    /**
     * 根据cid数组查询并且cIDreply=0的则是动态或者视频
     * @param array
     * @return
     */
    public  List<Comment> commselectarrcidlist(Integer[] array);

    /**
     * 根据评论id数组去查询数据 并且按照最新时间排序
     * @param array
     * @return
     */
    public List<Comment> comdatalisttime(Integer[] array);

    /**
     * 根据评论id数组去查询数据
     * @param array
     * @return
     */
    public List<Comment> comarrlist(Integer[] array);

    /**
     * 根据评论cid去查询下面的子评论对象集合
     * @param cIDreply
     * @return
     */
    public List<Comment> selelistcIDreply(Integer cIDreply);

    /**
     * 根据回复的id查询子评论数量
     * @param cIDreply
     * @return
     */
    public Integer commcountcIDreply(Integer cIDreply);

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
    public List<Comment> selectrpid(Integer cIDreply,Integer pn,Integer ps);

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
