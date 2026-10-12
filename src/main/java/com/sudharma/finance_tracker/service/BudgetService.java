
package com.sudharma.finance_tracker.service;

import com.sudharma.finance_tracker.entity.Budget;
import com.sudharma.finance_tracker.entity.User;
import com.sudharma.finance_tracker.repository.BudgetRepository;
import com.sudharma.finance_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public BudgetService(
            BudgetRepository budgetRepository,
            UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    public Budget createBudget(Long userId, Budget budget) {

        if (budget.getAmount() == null
                || budget.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Budget amount must be greater than zero"
            );
        }

        if (budget.getStartDate() == null
                || budget.getEndDate() == null
                || budget.getEndDate().isBefore(budget.getStartDate())) {
            throw new IllegalArgumentException(
                    "End date must be on or after start date"
            );
        }

        if (budget.getCategory() == null
                || budget.getCategory().isBlank()) {
            throw new IllegalArgumentException(
                    "Category is required"
            );
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        budget.setUser(user);

        return budgetRepository.save(budget);
    }

    public List<Budget> getUserBudgets(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public void deleteBudget(Long userId, Long budgetId) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() ->
                        new RuntimeException("Budget not found"));

        if (!budget.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You cannot delete another user's budget"
            );
        }

        budgetRepository.delete(budget);
    }
}
