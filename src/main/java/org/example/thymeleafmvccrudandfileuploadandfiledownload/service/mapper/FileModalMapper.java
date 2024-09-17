package org.example.thymeleafmvccrudandfileuploadandfiledownload.service.mapper;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.FileModalDto;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.FileModalEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileModalMapper {


    public FileModalEntity toEntity(FileModalDto dto) {
        return FileModalEntity.builder()
//                .id(dto.getId())
                .fileName(dto.getFileName())
                .fileType(dto.getFileType())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    public FileModalDto toDto(FileModalEntity entity) {
        return FileModalDto.builder()
                .id(entity.getId())
                .fileName(entity.getFileName())
                .fileType(entity.getFileType())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public List<FileModalDto>toDtos(List<FileModalEntity> entities) {
        List<FileModalDto> dtos = new ArrayList<>();
        for (FileModalEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }


}
