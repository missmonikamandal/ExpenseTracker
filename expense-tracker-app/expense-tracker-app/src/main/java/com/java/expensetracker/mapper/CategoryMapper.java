package com.java.expensetracker.mapper;

import com.java.expensetracker.dto.CategoryDto;
import com.java.expensetracker.entity.Category;

public class CategoryMapper {

    //Map CategoryDto to Category entity
    public static Category mapToCategory(CategoryDto categoryDto){
        return new Category(categoryDto.id(), categoryDto.name());
    }

    //Map Category entity to CategoryDto
    public static CategoryDto mapToCategoryDto(Category category){
        return new CategoryDto(category.getId(), category.getName());
    }

}
