package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.Avatar;
import com.stefura.mentorsplatform.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    Avatar findByUser(User user);

    List<Avatar> findByUserIn(List<User> users);
}
