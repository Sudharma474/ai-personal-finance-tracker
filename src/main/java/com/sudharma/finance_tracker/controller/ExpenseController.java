package com.sudharma.finance_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sudharma.finance_tracker.dto.ExpenseResponse;
import com.sudharma.finance_tracker.entity.Expense;
import com.sudharma.finance_tracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse createExpense(
            @RequestParam Long userId,
            @RequestBody Expense expense) {

        Expense savedExpense = expenseService.createExpense(userId, expense);

        return new ExpenseResponse(savedExpense);
    }

    @GetMapping
    public List<ExpenseResponse> getUserExpenses(
            @RequestParam Long userId) {

        return expenseService.getUserExpenses(userId)
                .stream()
                .map(ExpenseResponse::new)
                .toList();
    }

    @DeleteMapping("/{expenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(
            @RequestParam Long userId,
            @PathVariable Long expenseId) {

        expenseService.deleteExpense(userId, expenseId);
    }
}