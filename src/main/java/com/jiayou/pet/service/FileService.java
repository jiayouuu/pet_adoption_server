package com.jiayou.pet.service;

import com.jiayou.pet.controller.dto.file.FileUploadReq;
import com.jiayou.pet.common.R;

import java.io.File;
import java.io.IOException;

public interface FileService {
    R upload(FileUploadReq req);

    File getFileByName(String name) throws IOException;
}
