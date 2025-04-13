package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色菜单
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@TableName("menu")
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;

}
