package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@TableName("sys_menu")
@Schema(description = "菜单实体")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "子菜单列表")
    @TableField(exist = false)
    private List<Menu> children;

    @Schema(description = "父菜单ID")
    private Integer pid;

    @Schema(description = "页面路径")
    private String pagePath;

    @Schema(description = "排序号")
    private String sortNum;
}
