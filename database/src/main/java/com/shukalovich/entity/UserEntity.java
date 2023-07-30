package com.shukalovich.entity;

import com.shukalovich.entity.enam.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname", nullable = false, length = 40)
    private String surName;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "patronymic_name", nullable = false, length = 40)
    private String patronymicName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
