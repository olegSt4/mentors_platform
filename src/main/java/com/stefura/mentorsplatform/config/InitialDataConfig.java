package com.stefura.mentorsplatform.config;

import com.stefura.mentorsplatform.models.User;
import com.stefura.mentorsplatform.models.UserType;
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
            "john.doe@gmail.com",
            "stefura.3.1@gmail.com"
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

        saveInitialAvatar(1L, "eva-d.png");
        saveInitialAvatar(2L, "jake-s.png");
        saveInitialAvatar(3L, "isabella-m.png");
        saveInitialAvatar(4L, "tomas-p.png");

        LOGGER.info("Initial avatars successfully provided");
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

    @PostConstruct
    public void provideInitialSecurity() {
        LOGGER.info("Initial password providing...");

        for (String userEmail : INITIAL_USERS_EMAILS) {
            Optional<User> user = userRepository.findByEmail(userEmail);
            if (user.isEmpty() || !user.get().getPassword().equals(TEMP_UN_HASHED_PASS)) {
                break;
            }

            user.get().setLogin("login" + user.get().getId());
            user.get().setPassword(passwordEncoder.encode(INITIAL_USER_PASS));
            userRepository.save(user.get());
        }

        for (User client : userRepository.findByType(UserType.CLIENT)) {
            if (client.getPassword().startsWith("temp")) {
                client.setPassword(passwordEncoder.encode(INITIAL_USER_PASS));
                userRepository.save(client);
            }
        }

        LOGGER.info("Initial password providing completed!");
    }
}
