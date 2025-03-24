package com.jiayou.pet.controller.dto;

import lombok.Data;

/**
 * 用户密码香港
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
public class UserPasswordDTO {
    private String username;
    private String phone;
    private String password;
    private String newPassword;
}