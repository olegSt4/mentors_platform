package com.patternica.internship.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class AvatarDto {
    @ApiModelProperty(value="Avatar's id", required=true)
    private Long id;

    @ApiModelProperty(value="Id of avatar owner", required=true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AvatarDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarDto avatarDto = (AvatarDto) o;
        return id == avatarDto.id &&
                userId == avatarDto.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}
