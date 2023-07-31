package com.shukalovich.dto;

import com.shukalovich.entity.enam.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreationDto(@NotEmpty(message = "Surname cannot be empty.")
                              @Size(min = 2, max = 40, message = "Minimum surname length 2 characters, maximum surname length 40 characters")
                              @Pattern(regexp = "[a-zA-Z]{0,40}", message = "Only latin letters can be used.")
                              String surName,
                              @NotEmpty(message = "Name cannot be empty.")
                              @Size(min = 2, max = 20, message = "Minimum name length 2 characters, maximum name length 20 characters")
                              @Pattern(regexp = "[a-zA-Z]{2,20}", message = "Only latin letters can be used.")
                              String name,
                              @Size(max = 40, message = "Maximum patronymic name length 40 characters")
                              @Pattern(regexp = "[a-zA-Z]{0,40}", message = "Only latin letters can be used.")
                              String patronymicName,
                              Role role,
                              @NotEmpty(message = "Email should not be empty.")
                              @Email(message = "Email should be valid.")
                              String email) {
}