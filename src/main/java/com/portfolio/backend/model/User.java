
package com.portfolio.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    
    @Size(max = 50)
    private String jobPosition;
    
    @Size(max = 100)
    private String photoUrl;
    
    @Size(max = 200)
    private String personalProfile;
    
    @Size(max = 20)
    private String phoneNumber;
    
    @Size(max = 50)
    private String email;
    
    @Size(max = 30)
    private String birthDate;
    
    @Size(max = 100)
    private String linkedInUrl;
    
    @Size(max = 100)
    private String gitHubUrl;
    
    @Size(max = 100)
    private String location;
}
