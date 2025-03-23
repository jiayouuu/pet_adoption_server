package com.jiayou.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiayou.pet.controller.dto.UserDTO;
import com.jiayou.pet.controller.dto.UserPasswordDTO;
import com.jiayou.pet.entity.User;

public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);
}
