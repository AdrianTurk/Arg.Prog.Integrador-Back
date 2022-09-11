package com.portfolio.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.portfolio.backend.security.model.LoginUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    @Column(name = "user_id")
    private int id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "user_id")
    private LoginUser loginUser;

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
}