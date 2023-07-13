package com.example.graphql.dto;

import javax.validation.constraints.NotBlank;

public record CreateUserRequest(
       @NotBlank String username,
       @NotBlank String email,
        String city,
        String zipcode
) {
}
