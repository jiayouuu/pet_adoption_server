package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Sterilization;
import com.jiayou.pet.service.ISterilizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 救助内容 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "救助内容管理", description = "救助内容相关接口")
@RestController
@RequestMapping("/api/sterilization")
public class SterilizationController {

    @Resource
    private ISterilizationService sterilizationService;

    @Operation(summary = "保存救助内容")
    @PostMapping
    public R save(@RequestBody Sterilization sterilization) {
        sterilizationService.saveOrUpdate(sterilization);
        return R.success();
    }

    @Operation(summary = "删除救助内容")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        sterilizationService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除救助内容")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        sterilizationService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有救助内容")
    @GetMapping
    public R findAll() {
        return R.success(sterilizationService.list());
    }

    @Operation(summary = "根据ID查询救助内容")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(sterilizationService.getById(id));
    }

    @Operation(summary = "分页查询救助内容")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Sterilization> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("animal_name", name);
        }
        Page<Sterilization> page = sterilizationService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return R.success(page);
    }
}
