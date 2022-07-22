package com.Abhi.BlogApp.Controller;

import com.Abhi.BlogApp.services.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Abhi.BlogApp.config.AppConstant;
import com.Abhi.BlogApp.payloads.ApiResponse;
import com.Abhi.BlogApp.payloads.PostDto;
import com.Abhi.BlogApp.payloads.PostResponse;
import com.Abhi.BlogApp.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost
	(@RequestBody PostDto postDto,
     @PathVariable Integer userId,
     @PathVariable Integer categoryId
     )
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	
	
	//get post by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	//get post by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>>getPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto>posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	//get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue =AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,//required optional 
			@RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue =AppConstant.SORT_BY,required = false)String sortBy,
			@RequestParam(value="sortDir",defaultValue =AppConstant.SORT_DIR,required = false)String sortDir
			)
	{
		PostResponse allPost=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);

		return new  ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
		
	}
	
	//get by id post
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		
		PostDto postDto= this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK );
		
	}
	
	//delete
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted Successfully",true),HttpStatus.OK );
		
	}
	
	//update
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updateCategory(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		
		PostDto updateCategory=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updateCategory,HttpStatus.OK );
		
	}
	
	//searching
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>serchPostByTitle(@PathVariable("keywords")String keywords)
	{
		List<PostDto> results = this.postService.searchPosts(keywords);
		return  new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	
	//method to serve file
	
	@GetMapping(value="/post/image/{imageName}",produces =MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response) throws IOException
	{
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
