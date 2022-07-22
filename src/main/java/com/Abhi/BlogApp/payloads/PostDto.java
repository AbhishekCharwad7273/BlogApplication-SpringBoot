package com.Abhi.BlogApp.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.Abhi.BlogApp.entity.Category;
import com.Abhi.BlogApp.entity.Comment;
import com.Abhi.BlogApp.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	@NotBlank
	@Size(min=10,message="minimum size of post is 10")
	private String title;
	
	
	@NotBlank
	@Size(min=20,message="minimum size of post is 20")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();
	
	
}
