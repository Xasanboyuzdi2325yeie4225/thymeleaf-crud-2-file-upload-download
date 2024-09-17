package org.example.thymeleafmvccrudandfileuploadandfiledownload.service.mapper;


import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.UserEntity;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public UserEntity toEntity(UserDto dto) {
        return UserEntity.builder()
//                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }
    public UserEntity updateChecking(UserEntity entity,UserDto dto) {
        if (dto.getFirstName()!=null){
            entity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName()!=null){
            entity.setLastName(dto.getLastName());
        }
        if (dto.getEmail()!=null){
            entity.setEmail(dto.getEmail());
        }
        if (dto.getPassword()!=null){
            entity.setPassword(dto.getPassword());
        }
        if (dto.getRole()!=null){
            entity.setRole(dto.getRole());
        }
        if (dto.getCreatedAt()!=null){
            entity.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getUpdatedAt()!=null){
            entity.setUpdatedAt(dto.getUpdatedAt());
        }
        if (dto.getDeletedAt()!=null){
            entity.setDeletedAt(dto.getDeletedAt());
        }
        return entity;
    }


    public List<UserDto> toDtos(List<UserEntity> userlar) {
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userlar) {
            userDtos.add(toDto(userEntity));
        }
        return userDtos;
    }
}
