package com.jiayou.pet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.common.R;
import com.jiayou.pet.common.RoleEnum;
import com.jiayou.pet.controller.dto.UserDTO;
import com.jiayou.pet.controller.dto.UserPasswordDTO;
import com.jiayou.pet.entity.Menu;
import com.jiayou.pet.entity.User;
import com.jiayou.pet.mapper.RoleMapper;
import com.jiayou.pet.mapper.RoleMenuMapper;
import com.jiayou.pet.mapper.UserMapper;
import com.jiayou.pet.service.IMenuService;
import com.jiayou.pet.service.IUserService;
import com.jiayou.pet.utils.Encrypt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public R login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            // String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            // todo
            String token = "d";
            userDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            HashMap<String,Object> map = new HashMap<>();
            map.put("token",token);
            return R.success(map);
        } else {
            return R.error(400,"用户名或密码错误");
        }
    }

    @Override
    public R register(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenEmail = authentication.getName();
        if (!tokenEmail.equals(user.getEmail())) {
            return R.error(400, "邮箱不匹配,请重试");
        }
        if (null !=getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail()))) {
            return R.error(400,"邮箱已经注册，请直接登录");
        } 
        // 默认一个普通用户的角色
        user.setPassword(Encrypt.hashPassword(user.getPassword()));
        user.setNickname(user.getEmail());
        user.setUsername(user.getEmail());
        user.setRole(RoleEnum.ROLE_USER.toString());
        if(!save(user)){
            return R.error(400,"注册失败");
        }
        return R.success();
    }

    @Override
    public R updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            return R.error(400,"密码错误");
        }
        return R.success();
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        one = getOne(queryWrapper); // 从数据库查询用户信息
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * 
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf() 移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

}
