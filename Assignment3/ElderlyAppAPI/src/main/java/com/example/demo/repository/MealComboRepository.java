package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MealCombo;
import com.example.demo.model.MealCombo.MealType;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealComboRepository extends JpaRepository<MealCombo, Long> {
    List<MealCombo> findByMealType(MealCombo.MealType mealType);
    Optional<MealCombo> findByIdAndMealType(Long id, MealCombo.MealType mealType);
}