package com.stefura.mentorsplatform.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AspectDto {
    @ApiModelProperty(value="Aspect's id", example="1", required=true)
    private Long id;

    @NotNull
    @ApiModelProperty(value="Aspect's name", example="Well-being", required=true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AspectDto aspectDto = (AspectDto) o;
        return id == aspectDto.id &&
                name.equals(aspectDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
