package com.Abhi.BlogApp.services;

import java.util.List;



import com.Abhi.BlogApp.payloads.CategoryDto;


public interface CategoryService {//this interface used for loose coupling
	
	//interface by default take a public 
	
	
	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	 
	 //update
	 CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	 
	 //delete
	 	public void deleteCategory(Integer categoryId);
	 	
	 //get
	 	 CategoryDto getCategory(Integer categoryId);
	 	 
	 //getAll
	 	 
	 	 List<CategoryDto>getCategories();
	 	 
}
