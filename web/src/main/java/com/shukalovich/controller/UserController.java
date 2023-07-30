package com.shukalovich.controller;

import com.shukalovich.dto.UserCreationDto;
import com.shukalovich.dto.UserFilter;
import com.shukalovich.entity.UserEntity;
import com.shukalovich.service.UserService;
import com.shukalovich.util.PagesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(PagesUtil.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "rest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getAllUsers(UserFilter filter) {
        return userService.findByFilter(filter);
    }

    @GetMapping
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping(path = "/new")
    public String getUserSavePage(@ModelAttribute("user") UserCreationDto user) {
        return "user/new";
    }

    @PostMapping(path = "/new")
    public String saveUser(@ModelAttribute("user") @Valid UserCreationDto userDto,
    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/new";
        }

        return userService.createUser(userDto)
                .map(user -> "redirect:/user")
                .orElse("redirect:/user/new?error");

    }
}