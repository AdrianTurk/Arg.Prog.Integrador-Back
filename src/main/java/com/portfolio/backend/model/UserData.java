package com.portfolio.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String userName;

    @Size(min = 4, max = 50)
    private String fullName;

    @Size(max = 50)
    private String jobPosition;
    
    @Size(max = 100)
    private String photoUrl;
    
    @Size(max = 200)
    private String aboutMe;
    
    @Size(max = 20)
    private String phoneNumber;
    
    @Size(max = 50)
    @Email
    private String email;
    
    @Size(max = 30)
    private String birthDate;
    
    @Size(max = 100)
    private String linkedInUrl;
    
    @Size(max = 100)
    private String gitHubUrl;
    
    @Size(max = 100)
    private String location;

    public UserData(String userName) {
        this.userName = userName;
    }

    public UserData() {
    }

}