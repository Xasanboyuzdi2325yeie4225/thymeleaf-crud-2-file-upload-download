package org.example.thymeleafmvccrudandfileuploadandfiledownload.service.validation;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.UserDto;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.UserEntity;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {
    @Autowired
    private final UserRepository userRepository;

    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValid(UserDto dto) {
        if (this.userRepository.userlar().isEmpty()){
            return true;
        }
        else {
            for (UserEntity entity : this.userRepository.userlar()) {
                if (dto.getEmail().equals(entity.getEmail())) {
                    return false;
                }
            }
            return true;
        }

    }


}
