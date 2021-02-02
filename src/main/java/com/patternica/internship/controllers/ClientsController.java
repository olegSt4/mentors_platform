package com.patternica.internship.controllers;

import com.patternica.internship.dto.UserDto;
import com.patternica.internship.services.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@ApiIgnore
@RestController
@RequestMapping("/v1/clients")
public class ClientsController {
    private UserService userService;
    private DozerBeanMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClientsController(UserService userService, DozerBeanMapper mapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UserDto> getAllClients() {
        return userService.getAllClients().stream()
                .map((cl) -> mapper.map(cl, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{clientId}")
    public UserDto getClientById(@PathVariable Long clientId) {
        return mapper.map(userService.getUserById(clientId), UserDto.class);
    }
}
