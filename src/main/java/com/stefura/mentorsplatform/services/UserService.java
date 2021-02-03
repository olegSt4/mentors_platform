package com.stefura.mentorsplatform.services;

import com.stefura.mentorsplatform.exceptions.DuplicateUserDataException;
import com.stefura.mentorsplatform.exceptions.EntityNotFoundException;
import com.stefura.mentorsplatform.models.Profile;
import com.stefura.mentorsplatform.models.User;
import com.stefura.mentorsplatform.models.UserType;
import com.stefura.mentorsplatform.repositories.ProfileRepository;
import com.stefura.mentorsplatform.repositories.UserRepository;
import com.stefura.mentorsplatform.repositories.specifications.MentorSpecificationBuilder;
import com.stefura.mentorsplatform.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.stefura.mentorsplatform.repositories.specifications.ProfileSpecifications.*;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException(
                    "The user with this username(email) [" + email + "] does not exist");
        });

        return new SecurityUser(user);
    }

    public List<String> getAllProfessions() {
        return profileRepository.findAllProfessions();
    }

    public User getUserById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with such id (" + id + ") not found!"));
    }

    public List<User> getAllClients() {
        return userRepository.findByType(UserType.CLIENT);
    }

    public List<Profile> getAllMentorProfiles(String generalSearch, String profession, String lifeAspects,
                                              Double maxHourlyRate, Double minRating) {
        MentorSpecificationBuilder builder = new MentorSpecificationBuilder();

        if (generalSearch != null) {
            builder.addSpecification(Specification
                    .where(getByUserFullNameSpec(generalSearch))
                    .or(getByProfessionSpec(generalSearch))
                    .or(getByDescriptionSpec(generalSearch))
                    .or(getByLifeAspectSpec(generalSearch)));
        } else {
            if (profession != null) {
                builder.addSpecification(getByProfessionSpec(profession));
            }
            if (lifeAspects != null) {
                for (String aspect : lifeAspects.split(",")) {
                    builder.addSpecification(getByLifeAspectSpec(aspect.trim()));
                }
            }
            if (maxHourlyRate != null) {
                builder.addSpecification(getByRateLessThanOrEqualsToSpec(maxHourlyRate));
            }
            if (minRating != null) {
                builder.addSpecification(getByRatingHigherThanOrEqualsToSpec(minRating));
            }
        }

        return profileRepository.findAll(builder.build());
    }

    @Transactional
    public Profile getProfileByUserId(Long id) {
        Profile profile = profileRepository.findByUserId(id);
        if (profile == null) {
            throw new EntityNotFoundException("The user with such id (" + id + ") does not exist!");
        }

        profile.setViewsCount(profile.getViewsCount() + 1);
        profileRepository.save(profile);
      
        return profile;
    }

    @Transactional
    public User addUser(User userToAdd) {
        userToAdd.setRegistrationDate(new Date());
        userToAdd.setLastVisitDate(new Date());
        try {
            userRepository.save(userToAdd);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateUserDataException(ex.getMessage(), ex);
        }

  
        return userToAdd;
    }

    public User updateUserVisitByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityNotFoundException("The user such email (" + email + ") not found!");
        });

        user.setLastVisitDate(new Date());
        userRepository.save(user);

        return user;
    }
}
