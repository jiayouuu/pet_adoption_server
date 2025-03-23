package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章封面
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@TableName("article_kp")
@Schema(description = "文章封面实体")
public class ArticleKp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "标题")
    private String name;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "时间")
    private String time;

    @Schema(description = "封面")
    private String img;

    @Schema(description = "阅读数")
    private Integer read1;

}
