package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 救助
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "救助实体")
public class Rescue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String addres;

    @Schema(description = "照片")
    private String img;

    @Schema(description = "联系人")
    private String person;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "相关描述")
    private String information;

}
