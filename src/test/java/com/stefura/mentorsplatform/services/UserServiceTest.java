package com.stefura.mentorsplatform.services;

import com.stefura.mentorsplatform.exceptions.EntityNotFoundException;
import com.stefura.mentorsplatform.models.Aspect;
import com.stefura.mentorsplatform.models.Profile;
import com.stefura.mentorsplatform.models.User;
import com.stefura.mentorsplatform.models.UserType;
import com.stefura.mentorsplatform.repositories.AspectRepository;
import com.stefura.mentorsplatform.repositories.ProfileRepository;
import com.stefura.mentorsplatform.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProfileRepository profileRepository;
//
//    @Autowired
//    private AspectRepository aspectRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @BeforeAll
//    void initialSetup() {
//        Aspect testAspect1 = new Aspect("Well being");
//        Aspect testAspect2 = new Aspect("Positive mind");
//        aspectRepository.saveAll(Arrays.asList(testAspect1, testAspect2));
//
//
//        User testUser1 = new User("Mr. Mentor", "super.mentor.5@gmail.com", "super.mentor",
//                "mentorPass", UserType.MENTOR);
//        testUser1.setRating(5.0);
//        testUser1.setLastVisitDate(Date.from(LocalDate.now().minusDays(10)
//                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        Profile testProfile1 = new Profile("short description");
//        testProfile1.setRate(15.0);
//        testProfile1.setProfession("Sensei");
//        testProfile1.setViewsCount(12L);
//        testProfile1.setUser(testUser1);
//        testProfile1.addProfileAspect(testAspect1);
//
//        User testUser2 = new User("John Doe", "john.d.15@gmail.com", "johnny.doe",
//                "doePassword", UserType.MENTOR);
//        testUser2.setRating(3.5);
//        testUser2.setLastVisitDate(Date.from(LocalDate.now().minusDays(10)
//                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        Profile testProfile2 = new Profile("this description is longer than previous one");
//        testProfile2.setRate(12.0);
//        testProfile2.setProfession("Teacher");
//        testProfile2.setViewsCount(15L);
//        testProfile2.setUser(testUser2);
//        testProfile2.addProfileAspect(testAspect2);
//
//        profileRepository.saveAll(Arrays.asList(testProfile1, testProfile2));
//
//        User testUser3 = new User("Oleg Stefura", "stefura2010@ukr.net", "oleg.stefura",
//                "olegPassword", UserType.CLIENT);
//        userRepository.save(testUser3);
//
//        // java.net.MalformedURLException is trows without this
//        org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.getInstance();
//    }
//
//    @Test
//    @Order(1)
//    void getAllUsersTest() {
//        List<User> allUsers = userService.getAllUsers();
//        assertEquals(3, allUsers.size());
//    }
//
//    @Test
//    @Order(2)
//    @Transactional
//    void getAllMentorsTest() {
//        List<Profile> mentors = userService.getAllMentorProfiles(null, "Teacher",
//                null, null, null);
//        assertEquals(1, mentors.size());
//        assertEquals("John Doe", mentors.iterator().next().getUser().getFullName());
//
//        mentors = userService.getAllMentorProfiles(null, null, "Well being", null, null);
//        assertEquals(1, mentors.size());
//        assertEquals("Mr. Mentor", mentors.iterator().next().getUser().getFullName());
//
//        mentors = userService.getAllMentorProfiles(null, null, null, 15.0, null);
//        assertEquals(2, mentors.size());
//
//        mentors = userService.getAllMentorProfiles(null, null, null, null, 4.0);
//        assertEquals(1, mentors.size());
//        assertEquals("Mr. Mentor", mentors.iterator().next().getUser().getFullName());
//
//        mentors = userService.getAllMentorProfiles(null, null, null, 12.0, 4.0);
//        assertEquals(0, mentors.size());
//
//        mentors = userService.getAllMentorProfiles("Sensei", null, null, 12.0, 4.0);
//        assertEquals(1, mentors.size());
//        assertEquals(mentors.iterator().next().getDescription(), "short description");
//    }
//
//    @Test
//    @Order(3)
//    void getUserByIdTest() {
//        List<User> allUsers = userService.getAllUsers();
//
//        assertEquals(allUsers.iterator().next().getFullName(), userService.getUserById(allUsers.iterator().next()
//                .getId()).getFullName());
//        assertThrows(EntityNotFoundException.class, () -> {
//            userService.getUserById(-1L);
//        });
//    }
//
//    @Test
//    @Order(4)
//    void addUserTest() {
//        User newTestUser = new User("Ivan Inter", "vanjusha1999@ukr.net", "login.ivan",
//                "ivanPassword", UserType.CLIENT);
//
//        userService.addUser(newTestUser);
//
//        assertEquals(4, userRepository.count());
//        assertEquals(newTestUser.getFullName(), userRepository.findByEmail(newTestUser.getEmail())
//                .get().getFullName());
//    }
//
//    @Test
//    @Order(5)
//    void loadUserByUsernameTest() {
//        UserDetails testUser = userService.loadUserByUsername("login.ivan");
//        assertEquals("ivanPassword", testUser.getPassword());
//
//        assertThrows(UsernameNotFoundException.class, () -> {
//            userService.loadUserByUsername("unexisted.login");
//        });
//    }
//
//    @Test
//    @Order(6)
//    void getAllProfessionsTest() {
//        List<String> allProfessions = userService.getAllProfessions();
//
//        assertEquals(2, allProfessions.size());
//    }
//
//    @Test
//    @Order(7)
//    void getAllClientsTest() {
//        List<User> allClients = userService.getAllClients();
//
//        assertEquals(2, allClients.size());
//    }
//
//    @Test
//    @Order(8)
//    void getProfileByUserIdTest() {
//        List<User> allUsers = userService.getAllUsers();
//        User mrMentor = allUsers.stream()
//                .filter(user -> user.getFullName().equals("Mr. Mentor"))
//                .findFirst()
//                .get();
//
//        Profile mrMentorProfile = userService.getProfileByUserId(mrMentor.getId());
//        assertEquals("Sensei", mrMentorProfile.getProfession());
//    }
//
//    @Test
//    @Order(9)
//    void updateUserVisitByEmailTest() {
//        User mrMentor = userRepository.findByEmail("john.d.15@gmail.com").get();
//
//        Date previousVisit = mrMentor.getLastVisitDate();
//        mrMentor = userService.updateUserVisitByLogin(mrMentor.getLogin());
//
//        assertTrue(previousVisit.before(mrMentor.getLastVisitDate()));
//    }
}