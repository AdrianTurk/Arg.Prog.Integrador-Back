package com.portfolio.backend.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExperienceDTO {

    @NotNull
    private String companyName;

    private String period;

    private String jobPosition;

    @NotNull
    private String details;

    private String recomendations;

    public ExperienceDTO(){
    }

    public ExperienceDTO(@NotNull String companyName, @NotNull String details) {
        this.companyName = companyName;
        this.details = details;
    }

}
