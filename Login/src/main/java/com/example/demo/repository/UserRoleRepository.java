package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    List<UserRole> findByUserName(String name);
}
