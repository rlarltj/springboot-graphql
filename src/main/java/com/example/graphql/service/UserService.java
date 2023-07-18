package com.example.graphql.service;

import com.example.graphql.domain.Address;
import com.example.graphql.domain.User;
import com.example.graphql.dto.CreateUserRequest;
import com.example.graphql.dto.IdResponse;
import com.example.graphql.dto.UserResponse;
import com.example.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse findByLoginId(String loginId) {
        User findUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        UserResponse result = new UserResponse(findUser.getId(), findUser.getLoginId(), findUser.getUsername(), findUser.getEmail(), findUser.getAddress());
        return result;
    }

    public IdResponse register(CreateUserRequest createUserRequest) {
        Optional<User> result = userRepository.findByLoginId(createUserRequest.loginId());

        if (result.isPresent()) {
            throw new RuntimeException("이미 사용중인 아이디에요");
        }

        Address address = new Address(createUserRequest.city(), createUserRequest.zipcode());
        User user = new User(createUserRequest.loginId(), createUserRequest.username(), createUserRequest.email(), address);

        userRepository.save(user);
        return new IdResponse(user.getId());
    }

    public IdResponse delete(Long userId) {
        userRepository.deleteById(userId);

        return new IdResponse(userId);
    }
}
