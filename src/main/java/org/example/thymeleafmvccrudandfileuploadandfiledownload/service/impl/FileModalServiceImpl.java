package org.example.thymeleafmvccrudandfileuploadandfiledownload.service.impl;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.ApiResponse;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.FileModalDto;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.FileModalEntity;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.repository.FileModalRepository;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.FileModalService;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.mapper.FileModalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class FileModalServiceImpl implements FileModalService {
    @Autowired
    private final FileModalRepository fileModalRepository;
    @Autowired
    private final FileModalMapper fileModalMapper;

    public FileModalServiceImpl(FileModalRepository fileModalRepository, FileModalMapper fileModalMapper) {
        this.fileModalRepository = fileModalRepository;
        this.fileModalMapper = fileModalMapper;
    }

    @Override
    public ApiResponse<FileModalDto> add(MultipartFile dtos) {
        try {
            FileModalDto dto=new FileModalDto();
            dto.setFileName(dtos.getOriginalFilename());
            dto.setFileType(dtos.getContentType());
            dto.setContent(dtos.getBytes());
            dto.setCreatedAt(LocalDateTime.now());
            FileModalEntity save = this.fileModalRepository.save(this.fileModalMapper.toEntity(dto));
            return resulT(this.fileModalMapper.toDto(save));
        }catch (Exception e){
            return errorDb(e);
        }
    }
    public ApiResponse<List<FileModalDto>>result(List<FileModalDto>dtos){
        return ApiResponse.<List<FileModalDto>>builder()
                .count(this.fileModalRepository.getFaylModal().size())
                .content(dtos)
                .code(0)
                .success(true)
                .message("OK")
                .build();
    }
    public ApiResponse<FileModalDto>resulT(FileModalDto dtos){
        return ApiResponse.<FileModalDto>builder()
                .count(this.fileModalRepository.getFaylModal().size())
                .content(dtos)
                .code(0)
                .success(true)
                .message("OK")
                .build();
    }
    public ApiResponse<List<FileModalDto>>errorDB(Exception e){
        return ApiResponse.<List<FileModalDto>>builder()
                .code(-3)
                .message("DATABASE->"+e.getMessage())
                .success(false)
                .count(this.fileModalRepository.getFaylModal().size())
                .build();
    }
    public ApiResponse<FileModalDto>errorDb(Exception e){
        return ApiResponse.<FileModalDto>builder()
                .code(-3)
                .message("DATABASE->"+e.getMessage())
                .success(false)
                .count(this.fileModalRepository.getFaylModal().size())
                .build();
    }

    public ApiResponse<List<FileModalDto>>notfound(){
        return ApiResponse.<List<FileModalDto>>builder()
                .code(-1)
                .message("Not found")
                .success(false)
                .count(this.fileModalRepository.getFaylModal().size())
                .build();
    }
    public ApiResponse<FileModalDto>notfounD(){
        return ApiResponse.<FileModalDto>builder()
                .code(-1)
                .message("Not found")
                .success(false)
                .count(this.fileModalRepository.getFaylModal().size())
                .build();
    }


    @Override
    public ApiResponse<List<FileModalDto>> getFileAll() {
        try {

            if (this.fileModalRepository.getFaylModal().isEmpty()){
                return notfound();
            }

            return result(
                    this.fileModalMapper.toDtos(this.fileModalRepository.getFaylModal())
            );
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<FileModalDto> deleteFile(Integer id) {
        try {
            if (this.fileModalRepository.getFaylModal().isEmpty()){
                return notfounD();
            }
            FileModalEntity file = this.fileModalRepository.getFile(id);
            file.setDeletedAt(LocalDateTime.now());
            FileModalEntity save = this.fileModalRepository.save(file);
            return resulT(this.fileModalMapper.toDto(save));
        }catch (Exception e){
            return errorDb(e);
        }
    }
}
