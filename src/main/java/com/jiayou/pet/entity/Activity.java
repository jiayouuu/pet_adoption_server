package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 活动
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "活动对象")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "标题")
    private String name;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "时间")
    private String time;

    @Schema(description = "封面")
    private String img;

    @Schema(description = "报名人数")
    private Integer num;

    @Schema(description = "地址")
    private String address;
}
