package com.example.graphql.controller;

import com.example.graphql.domain.User;
import com.example.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping(name = "findUser")
    public User findUser(@Argument String loginId) {
        log.info("유저 조회 요청");
        return userService.findByLoginId(loginId);
    }
}
