package com.jiayou.pet.controller.dto.file;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class FileUploadReq {
    private MultipartFile chunk;
    private String fileId;
    private int chunkIndex;
    private int totalChunks;
    private String fileName;
    private String hash;
}
