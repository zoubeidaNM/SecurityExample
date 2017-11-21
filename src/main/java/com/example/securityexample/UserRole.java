package com.example.securityexample;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Collection;

@javax.persistence.Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String role;

    @ManyToMany (mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<DataUser> users;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<DataUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<DataUser> users) {
        this.users = users;
    }
}
