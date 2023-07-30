package com.shukalovich.dto;

import com.shukalovich.entity.enam.Role;

public record UserReadDto(String surName,
                          String name,
                          String patronymicName,
                          Role role,
                          String email
) {
}
