package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Comment;
import com.jiayou.pet.mapper.CommentMapper;
import com.jiayou.pet.service.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
