package com.jiayou.pet.service;

import com.jiayou.pet.common.R;
public interface ValidateService {
    R sendEmailCode(String email);
    R sendImgCode();
    R validateImgCode(String id, String code);
    R validateEmailCode(String id, String code);
}

