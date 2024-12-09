package com.java.expensetracker.service.impl;

import com.java.expensetracker.dto.CategoryDto;
import com.java.expensetracker.entity.Category;
import com.java.expensetracker.exceptions.ResourceNotFoundException;
import com.java.expensetracker.mapper.CategoryMapper;
import com.java.expensetracker.repository.CategoryRepository;
import com.java.expensetracker.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    //dependency injection by constructor
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        //Convert categoryDto to category entity
        Category category = CategoryMapper.mapToCategory(categoryDto);

        //save category object to database table - categories
        Category savedCategory = categoryRepository.save(category);

        //convert savedCategory entity to dto and return
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category Id not found with id :" + categoryId));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map((category) -> CategoryMapper.mapToCategoryDto(category))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        //get category entity from category database
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category Id not found with id :" + categoryId));

        //update category and save that into database
        category.setName(categoryDto.name());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        //check if id exists or not
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category Id not found with id :" + categoryId));

        categoryRepository.delete(category);

    }
}
