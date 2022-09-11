package com.portfolio.backend.security.model;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.portfolio.backend.model.Education;
import com.portfolio.backend.model.Experience;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.model.Skill;
import com.portfolio.backend.model.UserData;

import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "login_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<LoginRole> roles = new HashSet<>();

    @OneToMany(targetEntity = Experience.class)
    @JoinColumn(name = "user_id")
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(targetEntity = Education.class)
    @JoinColumn(name = "user_id")
    private Set<Experience> educations = new HashSet<>();

    @OneToMany(targetEntity = Project.class)
    @JoinColumn(name = "user_id")
    private Set<Experience> projects = new HashSet<>();

    @OneToMany(targetEntity = Skill.class)
    @JoinColumn(name = "user_id")
    private Set<Experience> skills = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "loginUser")
    private UserData userData;

    @NotNull
    @Column(unique = true)
    private String userName;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    public LoginUser(@NotNull String userName, @NotNull @Email String email, @NotNull String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String nombreUsuario) {
        this.userName = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<LoginRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<LoginRole> roles) {
        this.roles = roles;
    }
}
