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
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String entityName;

    private String title;

    @NotNull
    private String details;

    public Education() {
    }

    public Education(@NotNull String educationalEntity, String title, @NotNull String details) {
        entityName = educationalEntity;
        this.title = title;
        this.details = details;
    }

}
