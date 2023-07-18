package com.example.graphql.dto;

import com.example.graphql.domain.Address;

public record UserResponse(
        Long id,
        String loginId,
        String username,
        String email,
        Address address
) {
}
