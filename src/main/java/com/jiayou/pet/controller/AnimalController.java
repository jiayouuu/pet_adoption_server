package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.config.interceptor.AuthAccess;
import com.jiayou.pet.entity.Animal;
import com.jiayou.pet.service.IAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动物 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "动物管理", description = "动物相关接口")
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Resource
    private IAnimalService animalService;

    @Operation(summary = "保存动物信息")
    @PostMapping
    public R save(@RequestBody Animal animal) {
        animalService.saveOrUpdate(animal);
        return R.success();
    }

    @Operation(summary = "删除动物")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        animalService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除动物")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        animalService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有动物")
    @GetMapping
    public R findAll() {
        return R.success(animalService.list());
    }

    @Operation(summary = "根据ID查询动物")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(animalService.getById(id));
    }

    @Operation(summary = "用户端分页查询动物")
    @AuthAccess
    @GetMapping("/page/user")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Animal> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("nickname", name);
        }
        queryWrapper.eq("adopt", "可领养");
        return R.success(animalService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @Operation(summary = "管理端分页查询动物")
    @AuthAccess
    @GetMapping("/page")
    public R findPage(@RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String adopt,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Animal> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("nickname", name);
        }
        if (!"".equals(adopt)) {
            queryWrapper.eq("adopt", adopt);
        }
        return R.success(animalService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
