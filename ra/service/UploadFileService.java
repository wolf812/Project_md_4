package com.ra.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    String uploadFile(MultipartFile multipartFile);
    String uploadFileLocalToFirebase(String localFilePath);
}
