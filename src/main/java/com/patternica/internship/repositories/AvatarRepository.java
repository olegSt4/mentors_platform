package com.patternica.internship.repositories;

import com.patternica.internship.models.Avatar;
import com.patternica.internship.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    Avatar findByUser(User user);

    List<Avatar> findByUserIn(List<User> users);
}
