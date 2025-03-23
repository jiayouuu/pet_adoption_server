package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 捐款
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "捐款实体")
public class Donate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "捐款人")
    private String name;

    @Schema(description = "捐赠物资")
    private String goods;

    @Schema(description = "捐款事件")
    private String time;

}
