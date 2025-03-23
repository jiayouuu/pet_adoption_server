package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 字典
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@TableName("sys_dict")
public class Dict {

    private String name;
    private String value;
    private String type;

}
