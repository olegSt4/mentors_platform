package com.stefura.mentorsplatform.controllers;

import com.stefura.mentorsplatform.dto.ExtendedProfileDto;
import com.stefura.mentorsplatform.dto.MentorDto;
import com.stefura.mentorsplatform.dto.NewReviewDto;
import com.stefura.mentorsplatform.dto.ReviewDto;
import com.stefura.mentorsplatform.models.Avatar;
import com.stefura.mentorsplatform.models.Profile;
import com.stefura.mentorsplatform.models.Review;
import com.stefura.mentorsplatform.services.AspectService;
import com.stefura.mentorsplatform.services.ImageService;
import com.stefura.mentorsplatform.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/mentors")
public class MentorsController {
    private UserService userService;
    private ImageService imageService;
    private AspectService aspectService;
    private DozerBeanMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MentorsController(UserService userService,
                             ImageService imageService,
                             AspectService aspectService,
                             DozerBeanMapper mapper,
                             PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.imageService = imageService;
        this.aspectService = aspectService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    @ApiOperation(value="Provides data of all mentors",
            notes="This method shows main mentor's data, result can be filtered ", response= MentorDto.class)
    public List<MentorDto> getAllMentors(@ApiParam(value="The string of general search. " +
            "Will search on name, profession, description or life aspects")
            @RequestParam(required=false) String generalSearch,
            @ApiParam("User's profession")  @RequestParam(required=false) String profession,
            @ApiParam(value="Name of the life aspect") @RequestParam(required=false) String lifeAspects,
            @ApiParam(value="Upper bound of user's rate (price)") @RequestParam(required=false) Double maxHourlyRate,
            @ApiParam(value="Lower bound of user's rating") @RequestParam(required=false) Double minRating) {
        List<Profile> mentorProfiles = userService.getAllMentorProfiles(generalSearch, profession, lifeAspects,
                maxHourlyRate, minRating);

        List<MentorDto> result = mentorProfiles.stream()
                .map((p) -> mapper.map(p, MentorDto.class))
                .collect(Collectors.toList());

        List<Avatar> avatars = imageService.getAvatarsOfUsers(mentorProfiles.stream()
                .map(Profile::getUser)
                .collect(Collectors.toList()));

        avatars.forEach(avatar -> {
            result.stream()
                    .filter(mentor -> mentor.getId().equals(avatar.getUser().getId()))
                    .findFirst()
                    .ifPresent(mentor -> mentor.setAvatarId(avatar.getId()));
        });

        return result;
    }

    @GetMapping("/professions")
    @ApiOperation(value="Provides the list of all professions, registered it the system")
    public List<String> getAllProfessions() {
        return userService.getAllProfessions();
    }

    @GetMapping("/{mentorId}")
    @ApiOperation(value="Provides mentor's info by his/her id", response=MentorDto.class)
    public MentorDto getMentorById(@PathVariable Long mentorId) {
        Profile mentorProfile = userService.getProfileByUserId(mentorId);
        MentorDto result = mapper.map(mentorProfile, MentorDto.class);

        imageService.getAvatarOfUser(mentorProfile.getUser())
            .ifPresent(avatar -> result.setAvatarId(avatar.getId()));

        return result;
    }

    @GetMapping("/{mentorId}/profile")
    @ApiOperation(value="Provides mentor's profile by his/her id", response= ExtendedProfileDto.class)
    public ExtendedProfileDto getMentorProfile(@PathVariable Long mentorId) {
        Profile mentorProfile = userService.getProfileByUserId(mentorId);
        List<Review> reviews = mentorProfile.getProfileReviews();

        List<ReviewDto> reviewsDto = new ArrayList<>();
        List<Avatar> clientsAvatars = imageService.getAvatarsOfUsers(reviews.stream()
                .map(Review::getUser)
                .collect(Collectors.toList()));
        reviews.forEach(review -> {
            ReviewDto reviewDto = mapper.map(review, ReviewDto.class);
            clientsAvatars.stream()
                    .filter(clientsAvatar -> clientsAvatar.getUser().getId().equals(review.getUser().getId()))
                    .findFirst()
                    .ifPresent(clientsAvatar -> reviewDto.setOwnerAvatarId(clientsAvatar.getId()));

            reviewsDto.add(reviewDto);
        });

        ExtendedProfileDto result = mapper.map(mentorProfile, ExtendedProfileDto.class);
        result.setAspects(aspectService.getAspectsNamesByProfileId(mentorProfile.getId()));
        result.setReviews(reviewsDto);

        imageService.getAvatarOfUser(mentorProfile.getUser())
            .ifPresent(avatar -> result.setAvatarId(avatar.getId()));

        return result;
    }

    @PostMapping("/{mentorId}/profile/review")
    @ApiOperation(value="Provides possibility to add a new review to mentor's profile", response=ReviewDto.class)
    public ReviewDto addNewReview(@PathVariable Long mentorId, @RequestBody @Valid NewReviewDto newReviewDto) {
        Review newReview = mapper.map(newReviewDto, Review.class);
        newReview.setCreationTime(new Date());

        newReview = userService.addNewReviewToProfile(mentorId, newReview);

        return mapper.map(newReview, ReviewDto.class);
    }
}
