package com.jiayou.pet.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.User;
import com.jiayou.pet.mapper.UserMapper;
import com.jiayou.pet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.Data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 图表 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "图表管理", description = "图表相关接口")
@RestController
@RequestMapping("/api/echarts")
public class EchartsController {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;
    @Operation(summary = "获取会员季度统计")
    @GetMapping("/quarter")
    public R members() {
        return R.success(userMapper.selectUserCountGroupByQuarter());
    }
    @GetMapping("/sex")
    public R getSex() {
        return R.success(userMapper.selectUserCountGroupBySex());
    }
    
}
