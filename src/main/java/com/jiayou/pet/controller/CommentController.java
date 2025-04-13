package com.jiayou.pet.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Comment;
import com.jiayou.pet.entity.User;
import com.jiayou.pet.service.CommentService;
import com.jiayou.pet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "评论管理", description = "评论相关接口")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @Operation(summary = "保存评论")
    @PostMapping
    public R save(@RequestBody Comment comment) {
        // todo
        // comment.setUser();
        comment.setTime(DateUtil.now());
        commentService.saveOrUpdate(comment);
        return R.success();
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        commentService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除评论")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        commentService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有评论")
    @GetMapping
    public R findAll() {
        return R.success(commentService.list());
    }

    @Operation(summary = "查询文章评论")
    @GetMapping("/article/{type}/{articleId}")
    public R findAll(@PathVariable Integer type, @PathVariable Integer articleId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("type", type);
        List<Comment> list = commentService.list(queryWrapper);
        List<Comment> res = new ArrayList<>();
        for (Comment comment : list) {
            User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getNickname, comment.getUser()));
            if (one != null) {
                comment.setAvatar(one.getAvatarUrl()); // 设置头像
            }
            if (comment.getPid() == null) {
                res.add(comment);
                List<Comment> children = list.stream().filter(c -> comment.getId().equals(c.getPid()))
                        .collect(Collectors.toList());
                comment.setChildren(children);
            }
        }
        return R.success(res);
    }

    @Operation(summary = "根据ID查询评论")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(commentService.getById(id));
    }

    @Operation(summary = "分页查询评论")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(commentService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
