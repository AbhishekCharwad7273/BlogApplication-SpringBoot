package com.Abhi.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Abhi.BlogApp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>
{
	

}
