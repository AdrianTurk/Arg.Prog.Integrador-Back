package com.portfolio.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String companyName;

    private String jobPosition;
    
    private String period;

    
    @NotNull
    private String details;
    
    private String recomendations;

    public Experience(@NotNull String companyName,String jobPosition,  @NotNull String details,String period, String recomendations) {
        this.companyName = companyName;
        this.jobPosition = jobPosition;
        this.period = period;
        this.details = details;
        this.recomendations = recomendations;
    }


}
