package com.shukalovich.repository;

import com.shukalovich.dto.UserFilter;
import com.shukalovich.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryExtension {

    List<UserEntity> findByFilter(UserFilter filter);
}
