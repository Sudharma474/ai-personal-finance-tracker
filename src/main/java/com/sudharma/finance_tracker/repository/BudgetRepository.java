
package com.sudharma.finance_tracker.repository;

import com.sudharma.finance_tracker.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUserId(Long userId);
}
