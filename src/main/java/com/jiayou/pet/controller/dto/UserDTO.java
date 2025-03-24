package com.jiayou.pet.controller.dto;

import com.jiayou.pet.entity.Menu;

import lombok.Data;

import java.util.List;

/**
 * 接受前端登录请求的参数
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;
}