package com.dreamwolf.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.comment.pojo.Commentlike;

import java.util.List;

public interface CommentlikeService extends IService<Commentlike> {

    /**
     * 根据用户下的评论id来评论点赞表查被点赞的评论
     * @param array
     * @return
     */
    public List<Commentlike> selectlikelsit(Integer[] array);

}
