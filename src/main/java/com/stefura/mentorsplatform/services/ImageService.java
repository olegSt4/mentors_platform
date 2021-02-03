package com.stefura.mentorsplatform.services;

import com.stefura.mentorsplatform.exceptions.EntityNotFoundException;
import com.stefura.mentorsplatform.exceptions.ImageProcessingException;
import com.stefura.mentorsplatform.models.Avatar;
import com.stefura.mentorsplatform.models.User;
import com.stefura.mentorsplatform.repositories.AvatarRepository;
import com.stefura.mentorsplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Value("${upload.images.path}")
    private String imagesPath;

    private AvatarRepository avatarRepository;
    private UserRepository userRepository;

    @Autowired
    public ImageService(AvatarRepository avatarRepository, UserRepository userRepository) {
        this.avatarRepository = avatarRepository;
        this.userRepository = userRepository;
    }

    public Optional<Avatar> getAvatarOfUser(User user) {
        return avatarRepository.findByUser(user);
    }

    public List<Avatar> getAvatarsOfUsers(List<User> users) {
        return avatarRepository.findByUserIn(users);
    }

    public byte[] getAvatarById(Long id) {
        Avatar avatar = avatarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avatar with such id (" + id + ") is not found!"));

        File avatarsFolder = new File(imagesPath + "//" + avatar.getUser().getId() + "//avatar");

        File avatarFile = new File(avatarsFolder + "//" + avatar.getName());

        final byte[] avatarBytes;
        try {
            InputStream in = new FileInputStream(avatarFile);

            try (in) {
                avatarBytes = in.readAllBytes();
            }
        } catch (FileNotFoundException ex) {
            throw new ImageProcessingException("Couldn't find avatar file!", ex, HttpStatus.NOT_FOUND);
        } catch (IOException ex) {
            throw new ImageProcessingException("Error with opening avatar file!", ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return avatarBytes;
    }

    @Transactional
    public Avatar addAvatar(MultipartFile avatarFile, Long userId) {
        User avatarOwner = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with such id (" + userId + ") is not found!"));

        Avatar avatar = new Avatar(avatarFile.getOriginalFilename());
        avatar.setUser(avatarOwner);
        avatarRepository.save(avatar);

        File avatarsFolder = new File(imagesPath + "//" + userId + "//avatar");
        if (!avatarsFolder.exists()) {
            if (!avatarsFolder.mkdirs()) {
                throw new ImageProcessingException("Error while creating user directory!",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        try {
            avatarFile.transferTo(new File(avatarsFolder + "//" + avatarFile.getOriginalFilename()));
        } catch (IOException ex) {
            throw new ImageProcessingException("Error while saving user avatar!", ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return avatar;
    }
}
