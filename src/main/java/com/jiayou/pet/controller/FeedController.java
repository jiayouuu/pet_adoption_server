package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Feed;
import com.jiayou.pet.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 喂养 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "喂养管理", description = "喂养相关接口")
@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Resource
    private FeedService feedService;

    @Operation(summary = "保存喂养")
    @PostMapping
    public R save(@RequestBody Feed feed) {
        feedService.saveOrUpdate(feed);
        return R.success();
    }

    @Operation(summary = "删除喂养")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        feedService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除喂养")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        feedService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有喂养")
    @GetMapping
    public R findAll() {
        return R.success(feedService.list());
    }

    @Operation(summary = "根据ID查询喂养")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(feedService.getById(id));
    }

    @Operation(summary = "分页查询喂养")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Feed> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(feedService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
