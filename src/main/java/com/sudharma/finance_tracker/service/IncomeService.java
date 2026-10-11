
package com.sudharma.finance_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sudharma.finance_tracker.entity.Income;
import com.sudharma.finance_tracker.entity.User;
import com.sudharma.finance_tracker.repository.IncomeRepository;
import com.sudharma.finance_tracker.repository.UserRepository;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public IncomeService(
            IncomeRepository incomeRepository,
            UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public Income createIncome(Long userId, Income income) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        income.setUser(user);

        return incomeRepository.save(income);
    }

    public List<Income> getUserIncome(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    public void deleteIncome(Long userId, Long incomeId) {

        Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        if (!income.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You cannot delete another user's income"
            );
        }

        incomeRepository.delete(income);
    }
}
