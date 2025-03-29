package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Lost;
import com.jiayou.pet.service.ILostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 宠物走失 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "宠物走失管理", description = "宠物走失相关接口")
@RestController
@RequestMapping("/api/lost")
public class LostController {

    @Resource
    private ILostService lostService;

    @Operation(summary = "保存宠物走失信息")
    @PostMapping
    public R save(@RequestBody Lost lost) {
        lostService.saveOrUpdate(lost);
        return R.success();
    }

    @Operation(summary = "删除宠物走失信息")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        lostService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除宠物走失信息")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        lostService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有宠物走失信息")
    @GetMapping
    public R findAll() {
        return R.success(lostService.list());
    }

    @Operation(summary = "根据ID查询宠物走失信息")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(lostService.getById(id));
    }

    @Operation(summary = "分页查询宠物走失信息")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Lost> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("nickname", name);
        }
        return R.success(lostService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
