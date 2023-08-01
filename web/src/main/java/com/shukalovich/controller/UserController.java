package com.shukalovich.controller;

import com.shukalovich.dto.UserCreationDto;
import com.shukalovich.dto.UserFilter;
import com.shukalovich.entity.UserEntity;
import com.shukalovich.service.UserService;
import com.shukalovich.util.PagesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping(PagesUtil.USERS)
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getAllUsers(UserFilter filter) {

        logger.info("Request to get all users, actual quantity users {}", userService.findAll().size());

        return userService.findByFilter(filter);
    }

    @GetMapping(path = "/new")
    public String getUserSavePage(@ModelAttribute("user") UserCreationDto user) {
        return "user/new";
    }

    @PostMapping(path = "/new")
    public String saveUser(@ModelAttribute("user") @Valid UserCreationDto userCreationDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        logger.info("Request to add a new user with name {}, {}surname, {}email.",
                userCreationDto.name(), userCreationDto.surName(), userCreationDto.email());

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();

            logger.info("Number of errors when adding a new user {}.", allErrors.size());

            return "user/new";
        }

        logger.info("User with name {}, surname {}, email{}  successfully added",
                userCreationDto.name(), userCreationDto.surName(), userCreationDto.email());

        return userService.createUser(userCreationDto)
                .map(user -> "redirect:/")
                .orElse("redirect:/users/new");
    }
}