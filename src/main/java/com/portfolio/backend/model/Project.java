package com.portfolio.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(length = 30)
    @Size(min = 3, max = 30)
    String name;

    @Column(length = 300)
    @Size(min = 0, max = 300)
    String description;

    @Column(length = 300)
    @Size(min = 0, max = 300)
    String sourcelink;

    @Column(length = 300)
    @Size(min = 0, max = 300)
    String link;

    @Column(length = 300)
    @Size(min = 0, max = 300)
    String imgLink;

}
