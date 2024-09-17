package org.example.thymeleafmvccrudandfileuploadandfiledownload.controller;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.ApiResponse;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.dto.UserDto;
import org.example.thymeleafmvccrudandfileuploadandfiledownload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String add(Model model) {
        List<UserDto>dtos=this.userService.getAll().getContent();
        model.addAttribute("users", dtos);
        return "index";
    }

    @GetMapping(value = "/add1")
    public String add1(Model model) {
        model.addAttribute("user", new UserDto());
        return "create/add";
    }

    @PostMapping(value = "/add2")
    public String add2(Model model, UserDto dto) {
        ApiResponse<UserDto> userDtoApiResponse = this.userService.addUser(dto);
        String msg=userDtoApiResponse.getMessage();
        model.addAttribute("msg", msg);
        return "msg";
    }
    @GetMapping(value = "/get")
    public String get(Model model) {
        List<UserDto>dtos=this.userService.getAll().getContent();
        model.addAttribute("users", dtos);
        return "read/get";
    }
    @GetMapping(value = "/put1")
    public String put1(Model model,@RequestParam("id") Integer id) {
        ApiResponse<UserDto> userDtoApiResponse1 = this.userService.get(id);
        if (userDtoApiResponse1== null) {
            String msg=this.userService.get(id).getMessage();
            model.addAttribute("msg", msg);
            return "msg";
        }
        else {
            ApiResponse<UserDto> userDtoApiResponse = this.userService.get(id);
            UserDto content = userDtoApiResponse.getContent();
            content.setId(id);
            System.out.println("update uchun id orqali kelgan dto->"+content.toString());
            model.addAttribute("user", content);
            System.out.println("update1 id: "+content.getId());
            return "update/put";
        }
    }
    @PostMapping(value = "/put2")
    public String put2(Model model,@ModelAttribute UserDto dto) {
        System.out.println("update user id: "+dto.getId());
        System.out.println("frontdan kelgan yangi dto->"+dto.toString());
        ApiResponse<UserDto> update = this.userService.update(dto.getId(), dto);

        String msg=this.userService.get(dto.getId()).getMessage();
        model.addAttribute("msg",msg);
        return "msg";
    }

    @PostMapping(value = "/delete")
    public String delete(Model model,@Param("id") Integer id) {
        ApiResponse<UserDto> delete = this.userService.delete(id);
        String msg=this.userService.get(id).getMessage();
        model.addAttribute("msg",msg);
        return "msg";
    }

}
