package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 领养申请
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "领养申请对象")
public class Applcation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "养宠经验")
    private String experience;

    @Schema(description = "宠物")
    private String pet;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "婚姻")
    private String married;

    @Schema(description = "收入")
    private String income;

    @Schema(description = "职业")
    private String profession;

    @Schema(description = "住址")
    private String address;

    @Schema(description = "领养理由")
    private String reason;

    @Schema(description = "状态")
    private String state;

    @Schema(description = "动物id")
    private Integer animalId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "动物信息")
    @TableField(exist = false)
    private Animal animal;
}
