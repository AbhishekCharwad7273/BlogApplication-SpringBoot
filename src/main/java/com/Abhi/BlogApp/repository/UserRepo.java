package com.Abhi.BlogApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Abhi.BlogApp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User>findByEmail(String email);
}
