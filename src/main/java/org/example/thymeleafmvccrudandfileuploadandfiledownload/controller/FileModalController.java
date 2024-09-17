package org.example.thymeleafmvccrudandfileuploadandfiledownload.controller;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.ApiResponse;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.FileModalDto;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.FileModalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/file-modal")
public class FileModalController {

    @Autowired
    private final FileModalService fileModalService;

    public FileModalController(FileModalService fileModalService) {
        this.fileModalService = fileModalService;
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("files")MultipartFile files, Model model){
        String msg="";
        try {
            ApiResponse<FileModalDto> add = this.fileModalService.add(files);
            msg=add.getMessage();
        }catch (Exception e){
            System.out.println("file error->"+e.getMessage());
        }
        model.addAttribute("msg",msg);
        return "msg";
    }

    @GetMapping("/get")
    public String get(Model model){
        ApiResponse<List<FileModalDto>> fileAll = this.fileModalService.getFileAll();
        List<FileModalDto> content = fileAll.getContent();
        model.addAttribute("files",content);
        return "read/files-get";
    }

    @PostMapping("/del")
    public String delete(@RequestParam("id")Integer id, Model model){
        ApiResponse<FileModalDto> fileModalDtoApiResponse = this.fileModalService.deleteFile(id);
        String msg=fileModalDtoApiResponse.getMessage();
        model.addAttribute("msg",msg);
        return "msg";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("id")Integer id, Model model){
        FileModalDto dto=new FileModalDto();
        for (FileModalDto fileModalDto : this.fileModalService.getFileAll().getContent()) {
            if (fileModalDto.getId().equals(id)){
                dto=fileModalDto;break;
            }
        }
        ByteArrayResource resource=new ByteArrayResource(dto.getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+dto.getFileName()+"\"")
                .contentType(MediaType.parseMediaType(dto.getFileType()))
                .body(resource)
                ;
    }

}
