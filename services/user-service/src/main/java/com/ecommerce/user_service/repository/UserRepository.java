package com.ecommerce.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
