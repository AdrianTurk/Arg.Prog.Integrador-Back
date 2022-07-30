package com.portfolio.backend.security.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.portfolio.backend.security.enums.RoleFlag;

@Entity
public class LoginRole {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleFlag roleFlag;

    public LoginRole() {
    }

    public LoginRole(@NotNull RoleFlag roleFlag) {
        this.roleFlag = roleFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleFlag getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(RoleFlag roleFlag) {
        this.roleFlag = roleFlag;
    }
}
