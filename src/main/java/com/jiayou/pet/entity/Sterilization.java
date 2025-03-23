package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 救助内容
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "救助内容实体")
public class Sterilization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "流浪动物ID")
    private Integer animalId;

    @Schema(description = "捕捉状态")
    private String catch1;

    @Schema(description = "动物名称")
    private String animalName;

    @Schema(description = "绝育状态")
    private String sterilization;

    @Schema(description = "放生状态")
    private String release1;

    @Schema(description = "疫苗状态")
    private String vaccine;
}
