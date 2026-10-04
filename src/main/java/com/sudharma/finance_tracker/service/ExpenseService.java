package com.sudharma.finance_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sudharma.finance_tracker.entity.Expense;
import com.sudharma.finance_tracker.entity.User;
import com.sudharma.finance_tracker.repository.ExpenseRepository;
import com.sudharma.finance_tracker.repository.UserRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Expense createExpense(Long userId, Expense expense) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public void deleteExpense(Long userId, Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot delete another user's expense");
        }

        expenseRepository.delete(expense);
    }
}