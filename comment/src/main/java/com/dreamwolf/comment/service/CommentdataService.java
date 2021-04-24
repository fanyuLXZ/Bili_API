package com.dreamwolf.comment.service;

import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.pojo.Commentdata;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 根据评论id查询点赞数和点踩数
     * @param cID
     * @return
     */
    public Commentdata selectcID(Integer cID);

}
