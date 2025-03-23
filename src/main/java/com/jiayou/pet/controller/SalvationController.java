package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Salvation;
import com.jiayou.pet.service.ISalvationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 救助现场 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "救助现场管理", description = "救助现场相关接口")
@RestController
@RequestMapping("/salvation")
public class SalvationController {

    @Resource
    private ISalvationService salvationService;

    @Operation(summary = "保存救助现场信息")
    @PostMapping
    public R save(@RequestBody Salvation salvation) {
        salvationService.saveOrUpdate(salvation);
        return R.success();
    }

    @Operation(summary = "删除救助现场信息")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        salvationService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除救助现场信息")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        salvationService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有救助现场信息")
    @GetMapping
    public R findAll() {
        return R.success(salvationService.list());
    }

    @Operation(summary = "根据ID查询救助现场信息")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(salvationService.getById(id));
    }

    @Operation(summary = "分页查询救助现场信息")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Salvation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(salvationService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
