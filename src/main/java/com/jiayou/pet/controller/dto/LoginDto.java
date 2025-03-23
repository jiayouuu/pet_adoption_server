package com.jiayou.pet.controller.dto;
import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
    private String captchaId;
}
