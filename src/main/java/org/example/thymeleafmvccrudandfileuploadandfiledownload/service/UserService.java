package org.example.thymeleafmvccrudandfileuploadandfiledownload.service;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.ApiResponse;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ApiResponse<UserDto>addUser(UserDto dto);
    ApiResponse<UserDto>get(Integer id);
    ApiResponse<UserDto>get(String email);
    ApiResponse<UserDto>update(Integer id, UserDto dto);
    ApiResponse<UserDto>update(String email,String password, UserDto dto);
    ApiResponse<List<UserDto>> getAll();
    ApiResponse<UserDto> delete(Integer id);
    ApiResponse<UserDto> delete(String email, String password);
}
