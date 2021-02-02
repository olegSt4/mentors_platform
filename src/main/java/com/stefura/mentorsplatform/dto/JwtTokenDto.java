package com.stefura.mentorsplatform.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class JwtTokenDto {
    @ApiModelProperty(value="JWT Token. Use it to get access to secured pages")
    private String jwt;

    public JwtTokenDto() {
    }

    public JwtTokenDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtTokenDto that = (JwtTokenDto) o;
        return Objects.equals(jwt, that.jwt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwt);
    }
}
