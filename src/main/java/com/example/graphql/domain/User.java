package com.example.graphql.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String loginId;

    private String username;

    private String email;

    @Embedded
    private Address address;

    public User(String loginId, String username, String email, Address address) {
        this.loginId = loginId;
        this.username = username;
        this.email = email;
        this.address = address;
    }
}
