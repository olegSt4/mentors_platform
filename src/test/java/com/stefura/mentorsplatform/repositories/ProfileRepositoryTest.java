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

    @Test
    void findByRateTest() {
        Profile testProfile1 = new Profile("Description 1");
        testProfile1.setRate(17.0);
        Profile testProfile2 = new Profile("Description 2");
        testProfile2.setRate(12.5);
        Profile testProfile3 = new Profile("Description 2");
        testProfile3.setRate(17.0);

        profileRepository.save(testProfile1);
        profileRepository.save(testProfile2);
        profileRepository.save(testProfile3);

        assertEquals(2, profileRepository.findByRate(testProfile1.getRate()).size());
    }

    @Test
    void findByRateLessThanTest() {
        Profile testProfile1 = new Profile("Description 1");
        testProfile1.setRate(17.0);
        Profile testProfile2 = new Profile("Description 2");
        testProfile2.setRate(12.5);
        Profile testProfile3 = new Profile("Description 2");
        testProfile3.setRate(10.0);

        profileRepository.save(testProfile1);
        profileRepository.save(testProfile2);
        profileRepository.save(testProfile3);

        assertEquals(1, profileRepository.findByRateLessThan(testProfile2.getRate()).size());
    }

    @Test
    void findByRateGreaterThan() {
        Profile testProfile1 = new Profile("Description 1");
        testProfile1.setRate(17.0);
        Profile testProfile2 = new Profile("Description 2");
        testProfile2.setRate(12.5);
        Profile testProfile3 = new Profile("Description 2");
        testProfile3.setRate(10.0);

        profileRepository.save(testProfile1);
        profileRepository.save(testProfile2);
        profileRepository.save(testProfile3);

        assertEquals(1, profileRepository.findByRateGreaterThan(testProfile2.getRate()).size());
    }
}