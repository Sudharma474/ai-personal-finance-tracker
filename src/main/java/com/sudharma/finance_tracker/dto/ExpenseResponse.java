package com.sudharma.finance_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sudharma.finance_tracker.entity.Expense;

public class ExpenseResponse {

    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate expenseDate;
    private String category;
    private String paymentMethod;
    private Long userId;

    public ExpenseResponse(Expense expense) {
        this.id = expense.getId();
        this.amount = expense.getAmount();
        this.description = expense.getDescription();
        this.expenseDate = expense.getExpenseDate();
        this.category = expense.getCategory();
        this.paymentMethod = expense.getPaymentMethod();
        this.userId = expense.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public String getCategory() {
        return category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Long getUserId() {
        return userId;
    }
}