package com.example.demo.repository;

import com.example.demo.model.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealOrderRepository extends JpaRepository<MealOrder, Long> {
}