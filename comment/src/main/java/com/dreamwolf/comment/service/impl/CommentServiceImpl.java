package com.dreamwolf.comment.service.impl;

import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.mapper.CommentMapper;
import com.dreamwolf.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}