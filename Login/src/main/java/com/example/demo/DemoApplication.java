package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Component
class Test {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostConstruct
    public void printUserAndRoles() {
        this.insertUsers();
        System.out.println(userRepository.findAll());
        System.out.println(userRoleRepository.findAll());
    }

    public void insertUsers() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        userRepository.save(new User("user1", passwordEncoder.encode("pass1"), true));
        userRoleRepository.save(new UserRole("user1", "ADMIN"));

        userRepository.save(new User("user2", passwordEncoder.encode("pass2"), true));
        userRoleRepository.save(new UserRole("user2", "USER"));

    }

}
