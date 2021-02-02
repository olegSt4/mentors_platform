package com.patternica.internship.controllers;

import com.patternica.internship.dto.AuthDto;
import com.patternica.internship.dto.JwtTokenDto;
import com.patternica.internship.dto.NewUserDto;
import com.patternica.internship.models.User;
import com.patternica.internship.security.JwtProvider;
import com.patternica.internship.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class SecurityController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtProvider jwtProvider;
    private BCryptPasswordEncoder encoder;
    private DozerBeanMapper mapper;

    @Autowired
    public SecurityController(AuthenticationManager authenticationManager,
                              UserService userService,
                              JwtProvider jwtProvider,
                              BCryptPasswordEncoder encoder,
                              DozerBeanMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @PostMapping("/authentication")
    @ApiOperation(value="Provides possibility to authenticate the user", response=JwtTokenDto.class)
    public JwtTokenDto authenticate(@RequestBody @Valid AuthDto authDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(authDto.getLogin());
        userService.updateUserVisitByLogin(authDto.getLogin());
        return new JwtTokenDto(jwtProvider.generateToken(userDetails));
    }

    @PostMapping("/registration")
    @ApiOperation(value="Provide possibility to register new user", response=JwtTokenDto.class)
    public JwtTokenDto registerNewUser(@RequestBody @Valid NewUserDto newUserDto) {
        User userToAdd = mapper.map(newUserDto, User.class);
        userToAdd.setPassword(encoder.encode(userToAdd.getPassword()));

        userService.addUser(userToAdd);

        UserDetails userDetails = userService.loadUserByUsername(userToAdd.getLogin());
        return new JwtTokenDto(jwtProvider.generateToken(userDetails));
    }
}
