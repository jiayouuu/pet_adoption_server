package com.jiayou.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Data
@Schema(description = "文件实体")
@TableName("file")
public class Files {

    @TableId(type = IdType.AUTO)
    @Schema(description = "文件ID")
    private Integer id;

    @Schema(description = "文件名")
    private String name;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小(KB)")
    private Long size;

    @Schema(description = "文件URL")
    private String url;

    @Schema(description = "文件MD5值")
    private String md5;

    @Schema(description = "是否删除")
    private Boolean isDelete;

    @Schema(description = "是否启用")
    private Boolean enable;
}
