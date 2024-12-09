package com.java.expensetracker.mapper;

import com.java.expensetracker.dto.CategoryDto;
import com.java.expensetracker.dto.ExpenseDto;
import com.java.expensetracker.entity.Category;
import com.java.expensetracker.entity.Expense;

public class ExpenseMapper {
    //map expense entity to expense dto
    public static ExpenseDto mapToExpenseDto(Expense expense){
        return new ExpenseDto(expense.getId(),
                expense.getAmount(),
                expense.getExpenseDate(),
                new CategoryDto(expense.getCategory().getId(),
                        expense.getCategory().getName())
                );
    }


    //map expense dto to expense
    public static Expense mapToExpenseEntity(ExpenseDto expenseDto){
        Category category = new Category();
        category.setId(expenseDto.categoryDto().id());

        return new Expense(expenseDto.id(), expenseDto.amount(), expenseDto.expenseDate(), category);
    }
}
