package com.jiayou.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiayou.pet.entity.User;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Update("update user set password = #{password} where email = #{email}")
    int updatePassword(String email,String password);
    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);
    @Select("SELECT IF(sex IS NULL, '未知', sex) AS sex, COUNT(*) AS count " +
        "FROM user " +
        "GROUP BY IF(sex IS NULL, '未知', sex)")
    List<Map<String, Object>> selectUserCountGroupBySex();

    @Select("SELECT q.quarterName, IFNULL(u.count, 0) AS count " +
            "FROM (SELECT '第一季度' AS quarterName, 1 AS quarterNumber " +
            "      UNION ALL SELECT '第二季度', 2 " +
            "      UNION ALL SELECT '第三季度', 3 " +
            "      UNION ALL SELECT '第四季度', 4) q " +
            "LEFT JOIN (" +
            "    SELECT QUARTER(create_time) AS quarter, COUNT(*) AS count " +
            "    FROM user " +
            "    GROUP BY QUARTER(create_time)" +
            ") u ON q.quarterNumber = u.quarter")
    List<Map<String, Object>> selectUserCountGroupByQuarter();
    
}
