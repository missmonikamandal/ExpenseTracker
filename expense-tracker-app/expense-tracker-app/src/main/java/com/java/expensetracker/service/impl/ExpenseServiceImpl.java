package com.java.expensetracker.service.impl;

import com.java.expensetracker.dto.ExpenseDto;
import com.java.expensetracker.entity.Category;
import com.java.expensetracker.entity.Expense;
import com.java.expensetracker.exceptions.ResourceNotFoundException;
import com.java.expensetracker.mapper.ExpenseMapper;
import com.java.expensetracker.repository.CategoryRepository;
import com.java.expensetracker.repository.ExpenseRepository;
import com.java.expensetracker.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    //inject expenseRepository using constructor based injection
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;




    public ExpenseServiceImpl(ExpenseRepository expenseRepository,CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        //convert expenseDto to expense entity
        Expense expense = ExpenseMapper.mapToExpenseEntity(expenseDto);

        //save expense in the database
        Expense savedExpense = expenseRepository.save(expense);

        //return expenseDto
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()-> new ResourceNotFoundException("Expense ID NOT FOUND!" +expenseId));
        return ExpenseMapper.mapToExpenseDto(expense);

    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map((expense) ->ExpenseMapper.mapToExpenseDto(expense))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Expense ID NOT FOUND!" +id));

        //update expense
        expense.setAmount(expenseDto.amount());
        expense.setExpenseDate(expenseDto.expenseDate());
        if (expenseDto.categoryDto() != null){
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(()-> new ResourceNotFoundException("Category ID NOT FOUND!"+id));
            expense.setCategory(category);
        }
        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Expense ID NOT FOUND!" +id));
        expenseRepository.delete(expense);
    }
}
