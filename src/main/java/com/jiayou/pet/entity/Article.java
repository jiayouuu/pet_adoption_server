package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "文章实体")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "文章ID")
    private Integer id;

    @Schema(description = "文章标题")
    private String name;

    @Schema(description = "文章内容")
    private String content;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "发布时间")
    private String time;

    @Schema(description = "发布用户")
    private String user;

}
