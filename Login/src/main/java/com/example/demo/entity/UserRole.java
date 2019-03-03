package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {

    public UserRole() {
    }

    public UserRole(String userName, String role) {
        super();
        this.userName = userName;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;
    @Column(name = "username")
    private String userName;
    private String role;

    public Long getId() {
        return userRoleId;
    }

    public void setId(Long id) {
        this.userRoleId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoles [id=" + userRoleId + ", userName=" + userName + ", role=" + role + "]";
    }

}
