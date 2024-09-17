package org.example.thymeleafmvccrudandfileuploadandfiledownload.service.impl;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.ApiResponse;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.UserDto;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.UserEntity;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.repository.UserRepository;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.UserService;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.mapper.UserMapper;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final UserValidation userValidation;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidation = userValidation;
    }
    public ApiResponse<UserDto>errorValid(){
        return ApiResponse.<UserDto>builder()
                .code(-2)
                .count(this.userRepository.userlar().size())
                .message("validation error")
                .success(false)
                .build();
    }

    public ApiResponse<UserDto> errorDB(Exception e){
        return ApiResponse.<UserDto>builder()
                .success(false)
                .message("ERROR DATABASE"+e.getMessage())
                .build();
    }

    public ApiResponse<UserDto> notFound(){
        return ApiResponse.<UserDto>builder()
                .success(false)
                .message("NOT FOUND")
                .code(-1)
                .count(this.userRepository.userlar().size())
                .build();
    }

    public ApiResponse<List<UserDto>> notFounds(){
        return ApiResponse.<List<UserDto>>builder()
                .success(false)
                .message("NOT FOUND")
                .code(-1)
                .count(this.userRepository.userlar().size())
                .build();
    }

    public ApiResponse<List<UserDto>> errorsDB(Exception e){
        return ApiResponse.<List<UserDto>>builder()
                .code(-3)
                .count(-3)
                .message("DATABASE ERROR->"+e.getMessage())
                .success(false)
                .build();
    }
    public ApiResponse<List<UserDto>> results(List<UserEntity>entities) {
        return ApiResponse.<List<UserDto>>builder()
                .count(this.userRepository.userlar().size())
                .code(0)
                .message("OK")
                .content(this.userMapper.toDtos(entities))
                .build();
    }
    public ApiResponse<UserDto> result(UserEntity entities) {
        return ApiResponse.<UserDto>builder()
                .count(this.userRepository.userlar().size())
                .code(0)
                .message("OK")
                .content(this.userMapper.toDto(entities))
                .build();
    }
    @Override
    public ApiResponse<UserDto> addUser(UserDto dto) {
        try {
            if (this.userValidation.isValid(dto)){
                UserEntity entity = this.userMapper.toEntity(dto);
                entity.setCreatedAt(LocalDateTime.now());
                UserEntity save = this.userRepository.save(entity);
                return result(save);
            }
            return errorValid();
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<UserDto> get(Integer id) {
        try {
            if (this.userRepository.user(id)==null){
                return notFound();
            }
            UserEntity entity = this.userRepository.user(id);
            return result(entity);
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<UserDto> get(String email) {
        try {
            if (this.userRepository.findByEmailAndDeletedAtIsNull(email).isEmpty()){
                return notFound();
            }
            return result(this.userRepository.findByEmailAndDeletedAtIsNull(email).get());
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<UserDto> update(Integer id, UserDto dto) {
        try {
            System.out.println("update function");
            if (this.userRepository.user(id)==null){
                return notFound();
            }
            UserEntity entity = this.userRepository.user(id);
            System.out.println("update function(->entity{{"+entity.toString()+"}},\t\tdto{{"+dto.toString()+"}}");
            UserEntity userEntity = this.userMapper.updateChecking(entity, dto);
            userEntity.setUpdatedAt(LocalDateTime.now());
            UserEntity save = this.userRepository.save(userEntity);
            return result(save);
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<UserDto> update(String email, String password, UserDto dto) {
        try {
            if (this.userRepository.findByEmailAndDeletedAtIsNull(email).isEmpty()){
                return notFound();
            }
            if (password.equals(this.userRepository.findByEmailAndDeletedAtIsNull(email).get().getPassword())){
                UserEntity userEntity = this.userRepository.findByEmailAndDeletedAtIsNull(email).get();
                UserEntity userEntity1 = this.userMapper.updateChecking(userEntity, dto);
                userEntity1.setUpdatedAt(LocalDateTime.now());
                UserEntity save = this.userRepository.save(userEntity1);
                return result(save);
            }
            return errorPass();
        }catch (Exception e){
            return errorDB(e);
        }
    }
    public ApiResponse<UserDto>errorPass(){
        return ApiResponse.<UserDto>builder()
                .success(false)
                .message("ERROR PASSWORD ")
                .code(-2)
                .count(this.userRepository.userlar().size())
                .build();
    }

    @Override
    public ApiResponse<List<UserDto>> getAll() {

        try {

            if (this.userRepository.userlar().isEmpty()){
                return ApiResponse.<List<UserDto>>builder()
                        .code(-1)
                        .count(0)
                        .message("users empty")
                        .success(false)
                        .build();
            }

            return results(this.userRepository.userlar());

            }catch (Exception e){
            return errorsDB(e);
                    }


    }

    @Override
    public ApiResponse<UserDto> delete(Integer id) {
        try {
            if (this.userRepository.user(id)==null){
                return notFound();
            }
            UserEntity entity = this.userRepository.user(id);
            entity.setDeletedAt(LocalDateTime.now());
            UserEntity save = this.userRepository.save(entity);
            return result(save);
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<UserDto> delete(String email, String password) {
        try {
            if (this.userRepository.findByEmailAndDeletedAtIsNull(email).isEmpty()){
                return notFound();
            }
            if (password.equals(this.userRepository.findByEmailAndDeletedAtIsNull(email).get().getPassword())){
                UserEntity userEntity = this.userRepository.findByEmailAndDeletedAtIsNull(email).get();
                userEntity.setDeletedAt(LocalDateTime.now());
                UserEntity save = this.userRepository.save(userEntity);
                return result(save);
            }
            return errorPass();
        }catch (Exception e){
            return errorDB(e);
        }
    }

}
