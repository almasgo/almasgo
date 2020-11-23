package com.luthfihariz.almasgocore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(unique = true, length = 64)
    private String email;

    @Column(length = 64)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Engine> engines;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
