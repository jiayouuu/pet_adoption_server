package com.jiayou.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiayou.pet.entity.User;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Update("update user set password = #{password} where email = #{email}")
    int updatePassword(String email,String password);
    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);
}
