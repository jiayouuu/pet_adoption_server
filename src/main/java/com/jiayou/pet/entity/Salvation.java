package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 救助现场
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "救助现场实体")
public class Salvation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "情况描述")
    private String information;

    @Schema(description = "现场照片")
    private String img;

    @Schema(description = "地点")
    private String address;

    @Schema(description = "发现时间")
    private String time;

    @Schema(description = "联系人")
    private String person;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "解决状态")
    private String state;

}
