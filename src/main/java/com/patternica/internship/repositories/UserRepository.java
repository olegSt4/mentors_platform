package com.patternica.internship.repositories;

import com.patternica.internship.models.User;
import com.patternica.internship.models.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByLogin(String login);
    User findByPhone(String phone);
    List<User> findAll();
    List<User> findByType(UserType userType);
    List<User> findByFullName(String fullName);
    List<User> findByRating(Double rating);
    List<User> findByRatingGreaterThan(Double rating);
    List<User> findByRatingLessThan(Double rating);

    void deleteByEmail(String email);
}
