package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.Avatar;
import com.stefura.mentorsplatform.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    Optional<Avatar> findByUser(User user);

    List<Avatar> findByUserIn(List<User> users);
}
