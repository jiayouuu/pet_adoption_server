package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 评论
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "评论实体")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "评论ID")
    private Integer id;

    @Schema(description = "回复内容")
    private String content;

    @Schema(description = "回复人")
    private String user;

    @Schema(description = "回复时间")
    private String time;

    @Schema(description = "父评论ID")
    private Integer pid;

    @Schema(description = "文章ID")
    private Integer articleId;

    @Schema(description = "评论类型")
    private Integer type;

    @Schema(description = "用户头像")
    @TableField(exist = false)
    private String avatar;

    @Schema(description = "子评论列表")
    @TableField(exist = false)
    private List<Comment> children;
}
