package com.example.graphql.controller;

import com.example.graphql.domain.Address;
import com.example.graphql.domain.User;
import com.example.graphql.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureGraphQlTester
@Transactional
class UserControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private UserRepository userRepository;

    private String loginId;
    private String email;
    private String username;
    private String city;
    private String zipcode;

    private User user;

    @BeforeEach
    void setup() {
        loginId = "user123";
        email = "aaa@aaa.aaa";
        username = "kiseo";
        city = "seoul";
        zipcode = "12345";

        Address address = new Address(city, zipcode);
        user = new User(loginId, username, email, address);

        userRepository.save(user);
    }

    @Test
    @DisplayName("테스트")
    void test1() throws Exception {
        graphQlTester.documentName("findUser")
                .variable("loginId", loginId)
                .execute()
                .path("findUser.loginId")
                .entity(String.class)
                .isEqualTo(loginId)
                .path("findUser.username")
                .entity(String.class)
                .isEqualTo(username)
                .path("findUser.email")
                .entity(String.class)
                .isEqualTo(email)
                .path("findUser.address.city")
                .entity(String.class)
                .isEqualTo(city)
                .path("findUser.address.zipcode")
                .entity(String.class)
                .isEqualTo(zipcode);
    }
}