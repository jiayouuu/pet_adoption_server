package com.jiayou.pet.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.Constants;
import com.jiayou.pet.common.R;
import com.jiayou.pet.controller.dto.UserPasswordDTO;
import com.jiayou.pet.entity.User;
import com.jiayou.pet.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 用户 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "用户管理", description = "用户相关接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        try {
            return userService.login(user);
        } catch (Exception e) {
            return R.error(Constants.CODE_500, e.getMessage());
        }
    }
    @PostMapping("/test")
    public String postMethodName(@RequestBody HashMap <String, Object> entity) {
        return (String) entity.get("email");
    }
    
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public R register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            return R.error(Constants.CODE_500, e.getMessage());
        }
    }

    @Operation(summary = "保存用户")
    @PostMapping
    public R save(@RequestBody User user) {
        String username = user.getUsername();
        if (StrUtil.isBlank(username)) {
            return R.error(Constants.CODE_400, "参数错误");
        }
        if (user.getId() != null) {
            user.setPassword(null);
        } else {
            user.setNickname(user.getUsername());
            if (user.getPassword() == null) {
                user.setPassword("123");
            }
        }
        return R.success(userService.saveOrUpdate(user));
    }

    @Operation(summary = "修改密码")
    @PostMapping("/password")
    public R password(@RequestBody UserPasswordDTO userPasswordDTO) {
        return userService.updatePassword(userPasswordDTO);
    }

    @Operation(summary = "重置密码")
    @PutMapping("/reset")
    public R reset(@RequestBody UserPasswordDTO userPasswordDTO) {
        if (StrUtil.isBlank(userPasswordDTO.getUsername()) || StrUtil.isBlank(userPasswordDTO.getPhone())) {
            return R.error(-1, "参数异常");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userPasswordDTO.getUsername());
        queryWrapper.eq("phone", userPasswordDTO.getPhone());
        List<User> list = userService.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            User user = list.get(0);
            user.setPassword("123");
            userService.updateById(user);
        }
        return R.success();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success(userService.removeById(id));
    }

    @Operation(summary = "批量删除用户")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        return R.success(userService.removeByIds(ids));
    }

    @Operation(summary = "查询所有用户")
    @GetMapping
    public R findAll() {
        return R.success(userService.list());
    }

    @Operation(summary = "根据ID查询用户")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(userService.getById(id));
    }

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/username/{username}")
    public R findByUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return R.success(userService.getOne(queryWrapper));
    }

    @Operation(summary = "分页查询用户")
    @GetMapping("/page")
    public R findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String address) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }

        return R.success(userService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @Operation(summary = "导出用户数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @Operation(summary = "导入用户数据")
    @PostMapping("/import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }

        userService.saveBatch(users);
        return R.success(true);
    }
}
