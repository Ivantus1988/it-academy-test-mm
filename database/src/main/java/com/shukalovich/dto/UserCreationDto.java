package com.shukalovich.dto;

import com.shukalovich.entity.enam.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserCreationDto(@NotEmpty(message = "Surname cannot be empty.")
                              @Min(value = 2, message = "Minimum surname length 2 characters.")
                              @Max(value = 40, message = "Maximum surname length 40 characters.")
                              @Pattern(regexp = "[A-Za-z]", message = "Only latin letters can be used.")
                              String surName,
                              @NotEmpty(message = "Name cannot be empty.")
                              @Min(value = 2, message = "Minimum surname length 2 characters.")
                              @Max(value = 20, message = "Maximum surname length 40 characters.")
                              @Pattern(regexp = "[A-Za-z]", message = "Only latin letters can be used.")
                              String name,
                              @Min(value = 2, message = "Minimum surname length 2 characters.")
                              @Max(value = 40, message = "Maximum surname length 40 characters.")
                              @Pattern(regexp = "[A-Za-z]", message = "Only latin letters can be used.")
                              String patronymicName,
                              Role role,
                              @NotEmpty(message = "Email should not be empty.")
                              @Email(message = "Email should be valid.")
                              String email) {
}
