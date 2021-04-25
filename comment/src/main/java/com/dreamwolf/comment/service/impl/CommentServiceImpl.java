package com.dreamwolf.comment.service.impl;

import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.mapper.CommentMapper;
import com.dreamwolf.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户评论表
 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> commselectlistcid(Integer cid) {
        return commentMapper.commselectlistcid(cid);
    }

    @Override
    public List<Comment> commselectlistuid(Integer uID) {
        return commentMapper.commselectlistuid(uID);
    }

    @Override
    public Comment commcidlist(Integer cID) {
        return commentMapper.commcidlist(cID);
    }

    @Override
    public List<Comment> selectrpid(Integer cIDreply) {
        return commentMapper.selectrpid(cIDreply);
    }

    @Override
    public Integer selectbvidint(Integer[] cid) {
        return commentMapper.selectbvidint(cid);
    }

    @Override
    public List<Integer> selectuidlist(Integer uID) {
        return commentMapper.selectuidlist(uID);
    }

    @Override
    public List<Comment> commentuidlist(Integer[] array) {
        return commentMapper.commentuidlist(array);
    }
}
