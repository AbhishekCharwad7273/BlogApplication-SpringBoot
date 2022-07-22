package com.Abhi.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Abhi.BlogApp.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer >{

}
