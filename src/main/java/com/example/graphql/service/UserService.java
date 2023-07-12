package com.example.graphql.service;

import com.example.graphql.domain.User;
import com.example.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User findByLoginId(String loginId) {
        User findUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        return findUser;
    }

}
