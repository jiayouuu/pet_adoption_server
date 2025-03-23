package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.Constants;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Dict;
import com.jiayou.pet.entity.Menu;
import com.jiayou.pet.mapper.DictMapper;
import com.jiayou.pet.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "菜单管理", description = "菜单相关接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictMapper dictMapper;

    @Operation(summary = "保存菜单")
    @PostMapping
    public R save(@RequestBody Menu menu) {
        menuService.saveOrUpdate(menu);
        return R.success();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        menuService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除菜单")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有菜单ID")
    @GetMapping("/ids")
    public R findAllIds() {
        return R.success(menuService.list().stream().map(Menu::getId));
    }

    @Operation(summary = "查询所有菜单")
    @GetMapping
    public R findAll(@RequestParam(defaultValue = "") String name) {
        return R.success(menuService.findMenus(name));
    }

    @Operation(summary = "根据ID查询菜单")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(menuService.getById(id));
    }

    @Operation(summary = "分页查询菜单")
    @GetMapping("/page")
    public R findPage(@RequestParam String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return R.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @Operation(summary = "获取图标列表")
    @GetMapping("/icons")
    public R getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return R.success(dictMapper.selectList(queryWrapper));
    }
}
