package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.User;
import com.stefura.mentorsplatform.models.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByFullName(String fullName);
    List<User> findAll();
    List<User> findByType(UserType userType);
}
