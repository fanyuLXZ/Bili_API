package com.dreamwolf.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.comment.mapper.CommentlikeMapper;
import com.dreamwolf.comment.service.CommentlikeService;
import com.dreamwolf.entity.comment.Commentlike;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentlikeServiceImpl extends ServiceImpl<CommentlikeMapper, Commentlike> implements CommentlikeService {

    @Resource
    private CommentlikeMapper commentlikeMapper;

    @Override
    public List<Commentlike> selectlikelsit(Integer[] array) {
        return commentlikeMapper.selectlikelsit(array);
    }

    @Override
    public Commentlike commlikecidlist(Integer cid, Integer uid) {
        return commentlikeMapper.commlikecidlist(cid,uid);
    }


}
