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

    public List<UserReadDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserReadDto(
                        user.getSurName(),
                        user.getName(),
                        user.getPatronymicName(),
                        user.getRole(),
                        user.getEmail()
                ))
                .sorted(Comparator.comparing(UserReadDto::email))
                .collect(Collectors.toList());
    }

    public Optional<UserEntity> createUser(UserCreationDto registrationUser) {

        UserEntity user = getBuild(registrationUser);

        return Optional.of(userRepository.save(user));
    }

    public List<UserEntity> findByFilter(UserFilter filter) {
        return userRepository.findByFilter(filter);
    }

    private static UserEntity getBuild(UserCreationDto registrationUser) {
        return UserEntity.builder()
                .surName(registrationUser.surName())
                .name(registrationUser.name())
                .patronymicName(registrationUser.patronymicName())
                .role(registrationUser.role())
                .email(registrationUser.email())
                .build();
    }
}
