package com.jiayou.pet.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.User;
import com.jiayou.pet.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IUserService userService;

    @Operation(summary = "获取示例数据")
    @GetMapping("/example")
    public R get() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return R.success(map);
    }

    @Operation(summary = "获取会员季度统计")
    @GetMapping("/members")
    public R members() {
        List<User> list = userService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:
                    q1 += 1;
                    break;
                case Q2:
                    q2 += 1;
                    break;
                case Q3:
                    q3 += 1;
                    break;
                case Q4:
                    q4 += 1;
                    break;
                default:
                    break;
            }
        }
        return R.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }

}
