package com.jiayou.pet.controller;

import com.jiayou.pet.controller.dto.file.FileUploadReq;
import com.jiayou.pet.common.R;
import com.jiayou.pet.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
@RequestMapping("/api/files")
@EnableScheduling
public class FileController {
    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @PostMapping("/upload/chunk")
    public R upload(@ModelAttribute FileUploadReq req) {
        try {
            return fileService.upload(req);
        }catch (Exception e){
            return R.error(400,e.getMessage());
        }
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName){
        try {
            // 获取 File 对象
            File file = fileService.getFileByName(fileName);

            // 创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(fileInputStream);

            // 设置 HTTP 头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");

            // 返回文件流响应
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM) // 通用二进制流类型
                    .body(resource);
        }catch (IOException e){
            return ResponseEntity.notFound().build();
        }
    }
}