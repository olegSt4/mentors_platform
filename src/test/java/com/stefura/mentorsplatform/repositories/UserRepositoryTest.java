//package com.stefura.mentorsplatform.repositories;
//
//import com.stefura.mentorsplatform.models.User;
//import com.stefura.mentorsplatform.models.UserType;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void findByEmailTest() {
//        User testUser1 = new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                "stefura2010@ukr.net", UserType.CLIENT);
//        User testUser2 = new User("John Doe", "john.doe", "doePassword",
//                "john.doe@patternica.com", UserType.MENTOR);
//
//        userRepository.save(testUser1);
//        userRepository.save(testUser2);
//
//        assertEquals(testUser1.getId(), userRepository.findByEmail(testUser1.getEmail()).get().getId());
//    }
//
//    @Test
//    void findByPhoneTest() {
//        User testUser1 = new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                "stefura2010@ukr.net", UserType.CLIENT);
//        testUser1.setPhone("+380-989-8989-89");
//        User testUser2 = new User("John Doe", "john.doe", "doePassword",
//                "john.doe@patternica.com", UserType.MENTOR);
//
//        userRepository.save(testUser1);
//        userRepository.save(testUser2);
//
//        assertEquals(testUser1.getId(), userRepository.findByPhone(testUser1.getPhone()).getId());
//    }
//
//    @Test
//    void findAllTest() {
//        List<User> testUsers = Arrays.asList(
//                new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                        "stefura2010@ukr.net", UserType.CLIENT),
//                new User("John Doe", "john.doe", "doePassword",
//                        "john.doe@patternica.com", UserType.MENTOR),
//                new User("Unknown", "unknown.login", "unknown.pass",
//                        "unkwnown.2020@patternica.com", UserType.MENTOR)
//        );
//
//        userRepository.saveAll(testUsers);
//
//        assertEquals(3, userRepository.findAll().size());
//    }
//
//    @Test
//    void findByTypeTest() {
//        List<User> testUsers = Arrays.asList(
//                new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                        "stefura2010@ukr.net", UserType.CLIENT),
//                new User("John Doe", "john.doe", "doePassword",
//                        "john.doe@patternica.com", UserType.MENTOR)
//        );
//
//        userRepository.saveAll(testUsers);
//
//        assertEquals(1, userRepository.findByType(testUsers.get(0).getType()).size());
//        assertEquals(testUsers.get(0).getId(), userRepository.findByType(testUsers.get(0).getType()).get(0).getId());
//    }
//
//    @Test
//    void findByFullNameTest() {
//        List<User> testUsers = Arrays.asList(
//                new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                        "stefura2010@ukr.net", UserType.CLIENT),
//                new User("John Doe", "john.doe", "doePassword",
//                        "john.doe@patternica.com", UserType.MENTOR)
//        );
//
//        userRepository.saveAll(testUsers);
//
//        assertEquals(1, userRepository.findByFullName(testUsers.get(0).getFullName()).size());
//        assertEquals(testUsers.get(0).getId(), userRepository.findByFullName(testUsers.get(0).getFullName()).get(0).getId());
//    }
//
//    @Test
//    void findByRatingTest() {
//        User testUser1 = new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                "stefura2010@ukr.net", UserType.CLIENT);
//        testUser1.setRating(3.5);
//        User testUser2 = new User("John Doe", "john.doe", "doePassword",
//                "john.doe@patternica.com", UserType.MENTOR);
//        testUser2.setRating(5.0);
//
//        userRepository.save(testUser1);
//        userRepository.save(testUser2);
//
//        assertEquals(1, userRepository.findByRating(testUser1.getRating()).size());
//        assertEquals(testUser1.getId(), userRepository.findByRating(testUser1.getRating()).get(0).getId());
//    }
//
//    @Test
//    void findByRatingGreaterThanTest() {
//        User testUser1 = new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                "stefura2010@ukr.net", UserType.CLIENT);
//        testUser1.setRating(3.5);
//        User testUser2 = new User("John Doe", "john.doe", "doePassword",
//                "john.doe@patternica.com", UserType.MENTOR);
//        testUser2.setRating(5.0);
//        User testUser3 = new User("Unknown", "unknown.login", "unknown.pass",
//                "unkwnown.2020@patternica.com", UserType.MENTOR);
//        testUser3.setRating(2.0);
//
//        userRepository.save(testUser1);
//        userRepository.save(testUser2);
//        userRepository.save(testUser3);
//
//        assertEquals(2, userRepository.findByRatingGreaterThan(2.0).size());
//    }
//
//    @Test
//    void findByRatingLessThanTest() {
//        User testUser1 = new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                "stefura2010@ukr.net", UserType.CLIENT);
//        testUser1.setRating(3.5);
//        User testUser2 = new User("John Doe", "john.doe", "doePassword",
//                "john.doe@patternica.com", UserType.MENTOR);
//        testUser2.setRating(5.0);
//        User testUser3 = new User("Unknown", "unknown.login", "unknown.pass",
//                "unkwnown.2020@patternica.com", UserType.MENTOR);
//        testUser3.setRating(2.0);
//
//        userRepository.save(testUser1);
//        userRepository.save(testUser2);
//        userRepository.save(testUser3);
//
//        assertEquals(2, userRepository.findByRatingLessThan(5.0).size());
//    }
//
//    @Test
//    void deleteByEmailTest() {
//        User testUser1 = new User("Oleg Stefura", "olag.stefura", "olegPassword",
//                "stefura2010@ukr.net", UserType.CLIENT);
//        testUser1.setRating(3.5);
//        User testUser2 = new User("John Doe", "john.doe", "doePassword",
//                "john.doe@patternica.com", UserType.MENTOR);
//
//        userRepository.save(testUser1);
//        userRepository.save(testUser2);
//
//        assertEquals(2, userRepository.findAll().size());
//        userRepository.deleteByEmail(testUser1.getEmail());
//        assertEquals(1, userRepository.findAll().size());
//    }
//}