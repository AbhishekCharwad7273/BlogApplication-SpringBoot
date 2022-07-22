package com.Abhi.BlogApp.services;

import com.Abhi.BlogApp.payloads.CommentDto;

public interface CommentService{
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);
	

}
