package com.stefura.mentorsplatform.controllers;

import com.stefura.mentorsplatform.models.Aspect;
import com.stefura.mentorsplatform.services.AspectService;
import com.stefura.mentorsplatform.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/aspects")
public class AspectsController {
    private UserService userService;
    private AspectService aspectService;
    private DozerBeanMapper mapper;

    @Autowired
    public AspectsController(UserService userService, AspectService aspectService, DozerBeanMapper mapper) {
        this.userService = userService;
        this.aspectService = aspectService;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value="Provides the list of all aspects, registered in the system")
    public List<String> getAllLifeAspects() {
        return aspectService.getAllLifeAspects().stream()
                .map(Aspect::getName)
                .collect(Collectors.toList());
    }
}
