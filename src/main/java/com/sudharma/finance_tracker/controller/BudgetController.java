
package com.sudharma.finance_tracker.controller;

import com.sudharma.finance_tracker.dto.BudgetResponse;
import com.sudharma.finance_tracker.entity.Budget;
import com.sudharma.finance_tracker.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetResponse createBudget(
            @RequestParam Long userId,
            @RequestBody Budget budget) {

        Budget savedBudget = budgetService.createBudget(userId, budget);

        return new BudgetResponse(savedBudget);
    }

    @GetMapping
    public List<BudgetResponse> getUserBudgets(
            @RequestParam Long userId) {

        return budgetService.getUserBudgets(userId)
                .stream()
                .map(BudgetResponse::new)
                .toList();
    }

    @DeleteMapping("/{budgetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBudget(
            @RequestParam Long userId,
            @PathVariable Long budgetId) {

        budgetService.deleteBudget(userId, budgetId);
    }
}
