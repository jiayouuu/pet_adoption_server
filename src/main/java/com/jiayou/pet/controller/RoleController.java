package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Role;
import com.jiayou.pet.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "角色管理", description = "角色相关接口")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Operation(summary = "保存角色")
    @PostMapping
    public R save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return R.success();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除角色")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        roleService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有角色")
    @GetMapping
    public R findAll() {
        return R.success(roleService.list());
    }

    @Operation(summary = "根据ID查询角色")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(roleService.getById(id));
    }

    @Operation(summary = "分页查询角色")
    @GetMapping("/page")
    public R findPage(
            @RequestParam String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return R.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @Operation(summary = "设置角色菜单权限")
    @PostMapping("/roleMenu/{roleId}")
    public R roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return R.success();
    }

    @Operation(summary = "获取角色菜单权限")
    @GetMapping("/roleMenu/{roleId}")
    public R getRoleMenu(@PathVariable Integer roleId) {
        return R.success(roleService.getRoleMenu(roleId));
    }
}
