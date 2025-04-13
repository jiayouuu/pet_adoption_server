package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Donate;
import com.jiayou.pet.service.DonateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 捐款 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "捐款管理", description = "捐款相关接口")
@RestController
@RequestMapping("/api/donate")
public class DonateController {

    @Resource
    private DonateService donateService;

    @Operation(summary = "保存捐款")
    @PostMapping
    public R save(@RequestBody Donate donate) {
        donateService.saveOrUpdate(donate);
        return R.success();
    }

    @Operation(summary = "删除捐款")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        donateService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除捐款")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        donateService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有捐款")
    @GetMapping
    public R findAll() {
        return R.success(donateService.list());
    }

    @Operation(summary = "根据ID查询捐款")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(donateService.getById(id));
    }

    @Operation(summary = "分页查询捐款")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Donate> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(donateService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
