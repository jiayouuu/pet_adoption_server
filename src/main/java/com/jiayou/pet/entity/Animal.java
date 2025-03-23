package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 动物
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "动物对象")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "动物名字")
    private String nickname;

    @Schema(description = "动物性别")
    private String sex;

    @Schema(description = "种类")
    private String type;

    @Schema(description = "年龄")
    private String age;

    @Schema(description = "动物照片")
    private String img;

    @Schema(description = "活动范围")
    private String address;

    @Schema(description = "身体状态")
    private String status;

    @Schema(description = "是否绝育")
    private String sterilization;

    @Schema(description = "疫苗接种")
    private String vaccine;

    @Schema(description = "领养状态")
    private String adopt;

    @Schema(description = "其他描述")
    private String information;

    @Schema(description = "是否已领养")
    private String isAdopt;

    @Schema(description = "点赞数")
    private Integer praise;
}
