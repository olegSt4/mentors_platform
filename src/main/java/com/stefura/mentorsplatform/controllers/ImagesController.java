package com.stefura.mentorsplatform.controllers;

import com.stefura.mentorsplatform.dto.AvatarDto;
import com.stefura.mentorsplatform.exceptions.IllegalImageTypeException;
import com.stefura.mentorsplatform.services.ImageService;
import com.stefura.mentorsplatform.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/images")
public class ImagesController {
    private UserService userService;
    private ImageService imageService;
    private DozerBeanMapper mapper;

    @Autowired
    public ImagesController(UserService userService, ImageService imageService, DozerBeanMapper mapper) {
        this.userService = userService;
        this.imageService = imageService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value="Provides possibility to set user's avatar", response= AvatarDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message="Error while processing image")
    })
    public AvatarDto addUserAvatar(@ApiParam(value="File to be the user's avatar. Only .png and .jpg(jpeg) are allowed")
                                   @RequestParam("avatar") MultipartFile avatar,
                                   @ApiParam(value="User's id to bind avatar and user")
                                   @RequestParam("userId") Long userId) {
        if (!avatar.getContentType().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE) &&
            !avatar.getContentType().equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)) {
            throw new IllegalImageTypeException("The image should have .png or .jpeg(.jpg) extension!");
        }

        return mapper.map(imageService.addAvatar(avatar, userId), AvatarDto.class);
    }

    @GetMapping(
            value = "/{imageId}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ApiOperation(value="Provides user's photo according to passed id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message="Error while processing image")
    })
    public byte[] getAvatarById(@PathVariable Long imageId) {
        return imageService.getAvatarById(imageId);
    }
}
