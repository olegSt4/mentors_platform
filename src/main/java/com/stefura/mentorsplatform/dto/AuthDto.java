package com.stefura.mentorsplatform.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AuthDto {
    @NotNull
    @NotEmpty
    @ApiModelProperty(value="User's login (email). Used for authentication", example="random.mail@gmail.com", required=true)
    private String login;

    @NotNull
    @NotEmpty
    @ApiModelProperty(value="User's password. Used for authentication", example="password", required=true)
    private String password;

    public AuthDto() {
    }

    public AuthDto(@NotNull @NotEmpty String login, @NotNull @NotEmpty String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthDto authDto = (AuthDto) o;
        return Objects.equals(login, authDto.login) &&
                Objects.equals(password, authDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
