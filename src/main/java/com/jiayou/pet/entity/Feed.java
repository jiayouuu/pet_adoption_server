package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 喂养环境
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "喂养环境实体")
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "环境照片1")
    private String img;

    @Schema(description = "环境照片2")
    private String img2;

    @Schema(description = "环境照片3")
    private String img3;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "相关描述")
    private String information;

}
