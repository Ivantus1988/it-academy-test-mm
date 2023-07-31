package com.shukalovich.service;

import com.shukalovich.dto.UserCreationDto;
import com.shukalovich.dto.UserFilter;
import com.shukalovich.dto.UserReadDto;
import com.shukalovich.entity.UserEntity;
import com.shukalovich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> createUser(UserCreationDto registrationUser) {

        return Optional.of(userRepository.save(UserEntity.builder()
                .surName(registrationUser.surName())
                .name(registrationUser.name())
                .patronymicName(registrationUser.patronymicName())
                .role(registrationUser.role())
                .email(registrationUser.email())
                .build()));
    }

    public List<UserEntity> findByFilter(UserFilter filter) {
        return userRepository.findByFilter(filter)
                .stream()
                .sorted(Comparator.comparing(UserEntity::getEmail))
                .collect(Collectors.toList());
    }

}