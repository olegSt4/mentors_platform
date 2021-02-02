package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.Aspect;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class AspectRepositoryTest {
    @Autowired
    private AspectRepository aspectRepository;

    @Test
    void findByNameTest() {
        Aspect testAspect1 = new Aspect("Clean code");
        Aspect testAspect2 = new Aspect("Mental health");

        aspectRepository.save(testAspect1);
        aspectRepository.save(testAspect2);

        assertEquals(testAspect1.getId(), aspectRepository.findByName(testAspect1.getName()).getId());
    }

    @Test
    void deleteByNameTest() {
        Aspect testAspect1 = new Aspect("Clean code");
        Aspect testAspect2 = new Aspect("Mental health");

        aspectRepository.save(testAspect1);
        aspectRepository.save(testAspect2);

        assertEquals(2, aspectRepository.findAll().size());
        aspectRepository.deleteByName(testAspect1.getName());
        assertEquals(1, aspectRepository.findAll().size());
    }
}