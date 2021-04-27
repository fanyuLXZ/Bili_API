package com.dreamwolf.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.comment.mapper.CommentlikeMapper;
import com.dreamwolf.comment.pojo.Commentlike;
import com.dreamwolf.comment.service.CommentlikeService;
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



}
