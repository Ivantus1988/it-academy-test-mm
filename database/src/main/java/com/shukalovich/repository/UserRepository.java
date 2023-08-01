package com.shukalovich.repository;

import com.shukalovich.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryExtension {

    Optional<UserEntity> findByName(String name);

}
