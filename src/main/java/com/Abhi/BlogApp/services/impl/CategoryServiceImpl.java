package com.Abhi.BlogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Abhi.BlogApp.entity.Category;
import com.Abhi.BlogApp.exception.ResourceNotFoundException;
import com.Abhi.BlogApp.payloads.CategoryDto;
import com.Abhi.BlogApp.repository.CategoryRepo;
import com.Abhi.BlogApp.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		
		Category cat= this.modelMapper.map(categoryDto,Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","Category Id", categoryId));
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","Category Id", categoryId));
		
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> catagories =this.categoryRepo.findAll();
		List<CategoryDto>catDtos=catagories.stream().map((cat)->this.modelMapper.map(catagories, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
