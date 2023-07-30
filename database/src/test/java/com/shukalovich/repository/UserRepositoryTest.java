package com.shukalovich.repository;

import com.shukalovich.config.DatabaseConfig;
import com.shukalovich.entity.UserEntity;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.shukalovich.entity.enam.Role.ADMINISTRATOR;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@Sql(value = "classpath:sql/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/purge-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllUsersReturned() {
        String[] actual = userRepository.findAll()
                .stream()
                .map(UserEntity::getName)
                .toArray(String[]::new);
        String[] expected = List.of("Ivan", "Alexey")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenCreatedInvokedWithUser_ThenUserSaved() {

        UserEntity testUser = UserEntity.builder()
                .surName("Vasiliev")
                .name("Vasiliy")
                .patronymicName("Vasilievich")
                .role(ADMINISTRATOR)
                .email("vasiliev@mail.ru")
                .build();

        userRepository.save(testUser);

        List<String> users = userRepository.findAll()
                .stream()
                .map(UserEntity::getEmail)
                .toList();

        assertTrue(users.contains(testUser.getEmail()));
        userRepository.delete(testUser);
    }

    @Test
    @Order(3)
    void whenFindById_ThenReturnedValidUser() {
        Optional<UserEntity> actual = userRepository.findByName("Ivan");
        assertNotNull(actual);
        assertEquals("Ivan", actual.get().getName());
    }
}