package com.dreamwolf.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.comment.mapper.CommentMapper;
import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.pojo.Commentdata;
import com.dreamwolf.comment.mapper.CommentdataMapper;
import com.dreamwolf.comment.service.CommentdataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论数据表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@Service
public class CommentdataServiceImpl extends ServiceImpl<CommentdataMapper, Commentdata> implements CommentdataService {

    @Resource
    private CommentdataMapper commentdataMapper;

    @Override
    public List<Commentdata> commdatalistpage(Integer[] array, Integer next) {
        return commentdataMapper.commdatalistpage(array,next);
    }

    @Override
    public List<Commentdata> commdatalistarr(Integer[] array) {
        return commentdataMapper.commdatalistarr(array);
    }

    @Override
    public Commentdata selectcID(Integer cID) {
        return commentdataMapper.selectcID(cID);
    }

    @Override
    public List<Commentdata> commdatalist(Integer[] array) {
        return commentdataMapper.commdatalist(array);
    }


}
