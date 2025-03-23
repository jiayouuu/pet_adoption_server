package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 通知
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "通知实体")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "时间")
    private String time;

}
