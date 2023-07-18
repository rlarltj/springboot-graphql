package com.example.graphql.controller;

import com.example.graphql.dto.ApiResponse;
import com.example.graphql.dto.CreateUserRequest;
import com.example.graphql.dto.IdResponse;
import com.example.graphql.dto.UserResponse;
import com.example.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping(name = "findUser")
    public ApiResponse<UserResponse> findUser(@Argument String loginId) {
        UserResponse result = userService.findByLoginId(loginId);

        return ApiResponse.ok(result);
    }

    @MutationMapping(name = "registerUser")
    public ApiResponse<IdResponse> register(@Argument(name = "UserCreateInput") @Valid CreateUserRequest createUserRequest) {
        IdResponse result = userService.register(createUserRequest);
        return ApiResponse.ok(result);
    }

    @MutationMapping(name = "deleteUser")
    public ApiResponse<IdResponse> delete(@Argument Long userId) {
        IdResponse result = userService.delete(userId);
        return ApiResponse.ok(result);
    }

}
