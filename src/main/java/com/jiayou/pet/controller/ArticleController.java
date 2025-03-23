package com.jiayou.pet.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Article;
import com.jiayou.pet.service.IArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "文章管理", description = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @Operation(summary = "保存文章")
    @PostMapping
    public R save(@RequestBody Article article) {
        if (article.getId() == null) {
            article.setTime(DateUtil.now());
            //todo
            // article.setUserId(TokenUtils.getCurrentUser().getId());
            // article.setUser(TokenUtils.getCurrentUser().getNickname());
        }
        articleService.saveOrUpdate(article);
        return R.success();
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        articleService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除文章")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        articleService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有文章")
    @GetMapping
    public R findAll() {
        return R.success(articleService.list());
    }

    @Operation(summary = "根据ID查询文章")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(articleService.getById(id));
    }

    @Operation(summary = "分页查询文章")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(articleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
