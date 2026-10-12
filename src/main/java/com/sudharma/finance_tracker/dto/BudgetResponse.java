
package com.sudharma.finance_tracker.dto;

import com.sudharma.finance_tracker.entity.Budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetResponse {

    private Long id;
    private BigDecimal amount;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;

    public BudgetResponse(Budget budget) {
        this.id = budget.getId();
        this.amount = budget.getAmount();
        this.category = budget.getCategory();
        this.startDate = budget.getStartDate();
        this.endDate = budget.getEndDate();
        this.userId = budget.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getUserId() {
        return userId;
    }
}
