package org.example.thymeleafmvccrudandfileuploadandfiledownload.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private Boolean success;
    private Integer count;
    private T content;

}
