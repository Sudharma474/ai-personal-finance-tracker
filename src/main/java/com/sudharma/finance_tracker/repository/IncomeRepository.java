
package com.sudharma.finance_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudharma.finance_tracker.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long userId);
}
