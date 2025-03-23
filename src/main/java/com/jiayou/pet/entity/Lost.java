package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 宠物走失
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "宠物走失实体")
public class Lost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "走失宠物名字")
    private String nickname;

    @Schema(description = "种类")
    private String type;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "联系人")
    private String person;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "已丢失/带领回")
    private String status1;

    @Schema(description = "找回状态")
    private String status2;

}
