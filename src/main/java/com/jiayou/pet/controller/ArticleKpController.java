package com.jiayou.pet.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.ArticleKp;
import com.jiayou.pet.service.IArticleKpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章封面 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "文章封面管理", description = "文章封面相关接口")
@RestController
@RequestMapping("/api/articleKp")
public class ArticleKpController {

    @Resource
    private IArticleKpService articleKpService;

    @Operation(summary = "保存文章封面")
    @PostMapping
    public R save(@RequestBody ArticleKp articleKp) {
        if (articleKp.getId() == null) {
            articleKp.setTime(DateUtil.now());
        }
        articleKpService.saveOrUpdate(articleKp);
        return R.success();
    }

    @Operation(summary = "删除文章封面")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        articleKpService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除文章封面")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        articleKpService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有文章封面")
    @GetMapping
    public R findAll() {
        return R.success(articleKpService.list());
    }

    @Operation(summary = "根据ID查询文章封面")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        ArticleKp articleKp = articleKpService.getById(id);
        UpdateWrapper<ArticleKp> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("read1", articleKp.getRead1() + 1);
        articleKpService.update(wrapper);
        return R.success(articleKp);
    }

    @Operation(summary = "分页查询文章封面")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<ArticleKp> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(articleKpService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
