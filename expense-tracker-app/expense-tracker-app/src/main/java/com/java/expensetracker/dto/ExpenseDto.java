package com.java.expensetracker.dto;

import com.java.expensetracker.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(Long id,
                         BigDecimal amount,
                         LocalDate expenseDate,
                         CategoryDto categoryDto) {
}
