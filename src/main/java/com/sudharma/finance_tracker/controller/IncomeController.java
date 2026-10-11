
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

import com.sudharma.finance_tracker.dto.IncomeResponse;
import com.sudharma.finance_tracker.entity.Income;
import com.sudharma.finance_tracker.service.IncomeService;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncomeResponse createIncome(
            @RequestParam Long userId,
            @RequestBody Income income) {

        Income savedIncome = incomeService.createIncome(userId, income);

        return new IncomeResponse(savedIncome);
    }

    @GetMapping
    public List<IncomeResponse> getUserIncome(
            @RequestParam Long userId) {

        return incomeService.getUserIncome(userId)
                .stream()
                .map(IncomeResponse::new)
                .toList();
    }

    @DeleteMapping("/{incomeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncome(
            @RequestParam Long userId,
            @PathVariable Long incomeId) {

        incomeService.deleteIncome(userId, incomeId);
    }
}
