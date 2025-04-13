package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Activity;
import com.jiayou.pet.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "活动管理", description = "活动相关接口")
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @Operation(summary = "保存活动")
    @PostMapping
    public R save(@RequestBody Activity activity) {
        activityService.saveOrUpdate(activity);
        return R.success();
    }

    @Operation(summary = "删除活动")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        activityService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除活动")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        activityService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有活动")
    @GetMapping
    public R findAll() {
        return R.success(activityService.list());
    }

    @Operation(summary = "根据ID查询活动")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(activityService.getById(id));
    }

    @Operation(summary = "分页查询活动")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(activityService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
