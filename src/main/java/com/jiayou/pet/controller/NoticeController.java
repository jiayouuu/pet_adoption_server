package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Notice;
import com.jiayou.pet.service.INoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "公告管理", description = "公告相关接口")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private INoticeService noticeService;

    @Operation(summary = "保存公告")
    @PostMapping
    public R save(@RequestBody Notice notice) {
        noticeService.saveOrUpdate(notice);
        return R.success();
    }

    @Operation(summary = "删除公告")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        noticeService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除公告")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        noticeService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "获取前台公告")
    @GetMapping("/front")
    public R front() {
        return R.success(noticeService.limit(5));
    }

    @Operation(summary = "查询所有公告")
    @GetMapping
    public R findAll() {
        return R.success(noticeService.list());
    }

    @Operation(summary = "根据ID查询公告")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(noticeService.getById(id));
    }

    @Operation(summary = "分页查询公告")
    @GetMapping("/page")
    public R findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return R.success(noticeService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
