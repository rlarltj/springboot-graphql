package com.example.graphql;

import com.example.graphql.domain.Address;
import com.example.graphql.domain.User;
import com.example.graphql.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Dummy {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        Address address = new Address("seoul", "12345");
        User user = new User("test123", "홍길동", "test@aaa.aaa", address);

        userRepository.save(user);
    }
}
