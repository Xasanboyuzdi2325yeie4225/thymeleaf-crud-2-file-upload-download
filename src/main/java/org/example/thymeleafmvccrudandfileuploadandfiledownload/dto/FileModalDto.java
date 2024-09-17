package org.example.thymeleafmvccrudandfileuploadandfiledownload.dto;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileModalDto {
    private Integer id;
    private String fileName;
    private byte[] content;
    private String fileType;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
