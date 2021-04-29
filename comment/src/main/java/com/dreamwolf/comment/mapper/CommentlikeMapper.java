package com.dreamwolf.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.comment.pojo.Commentlike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentlikeMapper extends BaseMapper<Commentlike> {

    /**
     * 根据用户下的评论id来评论点赞表查被点赞的评论
     * @param array
     * @return
     */
    public List<Commentlike> selectlikelsit(Integer[] array);

    /***
     * 根据评论id和用户id查询被点赞的评论数据（）
     * @param cid
     * @param uid
     * @return
     */
    public Commentlike commlikecidlist(Integer cid,Integer uid);


}
