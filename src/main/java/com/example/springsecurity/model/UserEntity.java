package com.example.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Transient
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = passwordEncoder.encode(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean isEnabled() {
        if (enabled == 1) return true;
        else return false;
    }

    public void setEnabled(int enabled) {
        if (enabled == 1 || enabled == 0) {
            this.enabled = enabled;
        }
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
