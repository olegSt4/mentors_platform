package com.stefura.mentorsplatform.config;

import com.stefura.mentorsplatform.models.User;
import com.stefura.mentorsplatform.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Configuration
public class InitialDataConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataConfig.class);
    private static final String INITIAL_AVATARS_PATH = "temp/initial-avatars/";
    private static final String TEMP_UN_HASHED_PASS = "tempUnHashedPass";
    private static final String INITIAL_USER_PASS = "password";
    private static final Set<String> INITIAL_USERS_EMAILS = Set.of(
            "eva.d.1990@gmail.com",
            "jake.s.1984@gmail.com",
            "isabella.m@gmail.com",
            "tomas.p.1986@gmail.com",
            "oleg.surname@gmail.com",
            "ivanov.ivan@urk.net"
    );
    private static final Map<Long, String> INITIAL_USERS_AVATARS_NAMES = Map.of(
            1L, "eva-d.png",
            2L, "jake-s.png",
            3L, "isabella-m.png",
            4L, "tomas-p.png",
            5L, "oleg-s.jpg",
            6L, "ivan-i.jpg"
    );

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDataConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${upload.images.path}")
    private String imagesPath;

    @PostConstruct
    public void saveInitialAvatars() {
        File rootImagesFolder = new File(imagesPath);
        if (!rootImagesFolder.exists()) {
            if (!rootImagesFolder.mkdirs()) {
                LOGGER.error("The images folder cannot be created");
                return;
            }
        }

        if (rootImagesFolder.list().length > 0) {
            return;
        }

        LOGGER.info("Setting up initial avatars...");

        INITIAL_USERS_AVATARS_NAMES.forEach(this::saveInitialAvatar);

        LOGGER.info("Initial avatars successfully provided");
    }

    @PostConstruct
    public void provideInitialHashedPasswords() {
        LOGGER.info("Initial password providing...");

        for (String userEmail : INITIAL_USERS_EMAILS) {
            Optional<User> user = userRepository.findByEmail(userEmail);
            if (user.isEmpty() || !user.get().getPassword().equals(TEMP_UN_HASHED_PASS)) {
                break;
            }

            user.get().setPassword(passwordEncoder.encode(INITIAL_USER_PASS));
            userRepository.save(user.get());
        }

        LOGGER.info("Initial password providing completed!");
    }

    private void saveInitialAvatar(Long userId, String fileName) {
        String userAvatarDirectoryPath = imagesPath + "//" + userId + "//avatar";

        File userAvatarDir = new File(userAvatarDirectoryPath);
        if (!userAvatarDir.mkdirs()) {
            LOGGER.error("User's directory cannot be created! (id:" + userId);
            return;
        }

        InputStream avatarStream = getClass().getClassLoader()
                .getResourceAsStream(INITIAL_AVATARS_PATH + fileName);
        if (avatarStream == null) {
            LOGGER.error("Cannot find avatar file: " + fileName);
            return;
        }

        try(avatarStream;
            FileOutputStream fos = new FileOutputStream(new File(userAvatarDirectoryPath + "//" + fileName))) {
            fos.write(avatarStream.readAllBytes());
        } catch (IOException ex) {
            LOGGER.error("Error while saving initial avatar: " + fileName, ex);
        }
    }
}
