
package com.sudharma.finance_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sudharma.finance_tracker.entity.Income;

public class IncomeResponse {

    private Long id;
    private BigDecimal amount;
    private String source;
    private LocalDate incomeDate;
    private String description;
    private Long userId;

    public IncomeResponse(Income income) {
        this.id = income.getId();
        this.amount = income.getAmount();
        this.source = income.getSource();
        this.incomeDate = income.getIncomeDate();
        this.description = income.getDescription();
        this.userId = income.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }
}
