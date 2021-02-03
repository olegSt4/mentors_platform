package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.Profile;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProfileRepositoryTest {
    @Autowired
    private ProfileRepository profileRepository;
}