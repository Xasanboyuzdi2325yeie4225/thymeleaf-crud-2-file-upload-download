package org.example.thymeleafmvccrudandfileuploadandfiledownload.service;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.ApiResponse;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.FileModalDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileModalService {
    public ApiResponse<FileModalDto> add(MultipartFile file);
    public ApiResponse<List<FileModalDto>> getFileAll();
    public ApiResponse<FileModalDto>deleteFile(Integer id);
}
