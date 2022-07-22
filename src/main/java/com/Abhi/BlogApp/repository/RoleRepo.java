package com.Abhi.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Abhi.BlogApp.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>
{

}
