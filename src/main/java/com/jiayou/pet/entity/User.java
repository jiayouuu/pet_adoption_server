package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@TableName("user")
@Schema(description = "用户对象")
public class User implements Serializable {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "头像")
    private String avatarUrl;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "生日")
    private String birth;
}
