package com.jiayou.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiayou.pet.controller.dto.UserPasswordDTO;
import com.jiayou.pet.entity.User;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);
    @Select("SELECT * FROM sys_user WHERE email = #{email}")
    User findByEmail(String email);
}
