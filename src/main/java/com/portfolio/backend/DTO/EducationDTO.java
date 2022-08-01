package com.portfolio.backend.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationDTO {

    @NotNull
    private String entityName;
    private String title;

    @NotNull
    private String details;

    public EducationDTO() {
    }

    public EducationDTO(@NotNull String entityName, String title, @NotNull String details) {
        this.entityName = entityName;
        this.title = title;
        this.details = details;
    }


}
