package com.jiayou.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.User;

public interface IUserService extends IService<User> {

    R login(User user);

    R register(User user);

    R updatePassword(String password);
}
