package com.dreamwolf.comment.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.comment.Commentdata;

import java.util.List;

/**
 * <p>
 * 评论数据表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
public interface CommentdataService extends IService<Commentdata> {

    /**
     * 根据评论id集合查询热度最高的前10条
     * @param array 热度等于 点赞数减去点踩数
     *    next 页码
     * @return
     */
    public List<Commentdata> commdatalistpage(Integer[] array,Integer next,Integer ps);


    /**
     * 根据评论id集合查询热度最高的前10条
     * @param array 热度等于 点赞数减去点踩数
     * @return
     */
    public List<Commentdata> commdatalistarr(Integer[] array);

    /**
     * 根据评论id查询点赞数和点踩数
     * @param cID
     * @return
     */
    public Commentdata selectcID(Integer cID);

    /**
     * 通过子评论集合去查询子评论里面热度最高的数据取前三条
     * @param array  热度等于 点赞数减去点踩数
     * @return
     */
    public List<Commentdata> commdatalist(Integer[] array);

}
