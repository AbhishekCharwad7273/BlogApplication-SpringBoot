package com.Abhi.BlogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Abhi.BlogApp.entity.Category;
import com.Abhi.BlogApp.entity.Post;
import com.Abhi.BlogApp.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	
	List<Post>findByUser(User user);
	
	
	List<Post>findByCategory(Category category);
	
	
	//List<Post>findByTitleContaining(String Title);
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	

	

}
