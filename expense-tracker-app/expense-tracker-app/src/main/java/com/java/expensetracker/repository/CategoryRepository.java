package com.java.expensetracker.repository;

import com.java.expensetracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Spring data jpa provides implementation for this interface
    //CRUD methods to perform CRUD database operations on Category entity
    //Spring data data provides transaction for all the CRUD operation
}
