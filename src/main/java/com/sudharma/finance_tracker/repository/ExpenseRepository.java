package com.sudharma.finance_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudharma.finance_tracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);
}