package com.portfolio.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String companyName;
    
    private String period;
    
    @NotNull
    private String details;
    
    private String references;

    public Experience() {
    }

    public Experience(@NotNull String companyName,  @NotNull String details,String period, String references) {
        this.companyName = companyName;
        this.period = period;
        this.details = details;
        this.references = references;
    }


}
