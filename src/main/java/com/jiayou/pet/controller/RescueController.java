package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Rescue;
import com.jiayou.pet.service.IRescueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 救助 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "救助管理", description = "救助相关接口")
@RestController
@RequestMapping("/rescue")
public class RescueController {

    @Resource
    private IRescueService rescueService;

    @Operation(summary = "保存救助信息")
    @PostMapping
    public R save(@RequestBody Rescue rescue) {
        rescueService.saveOrUpdate(rescue);
        return R.success();
    }

    @Operation(summary = "删除救助信息")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        rescueService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除救助信息")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        rescueService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有救助信息")
    @GetMapping
    public R findAll() {
        return R.success(rescueService.list());
    }

    @Operation(summary = "根据ID查询救助信息")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(rescueService.getById(id));
    }

    @Operation(summary = "分页查询救助信息")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Rescue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(rescueService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
