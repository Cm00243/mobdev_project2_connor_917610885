package com.example.demo.service;

import com.example.demo.model.ComboItem;
import com.example.demo.model.MealCombo;
import com.example.demo.repository.MealComboRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MealComboService {
    private final MealComboRepository mealComboRepository;

    public MealComboService(MealComboRepository mealComboRepository) {
        this.mealComboRepository = mealComboRepository;
    }

    public List<MealCombo> findAll() {
        return mealComboRepository.findAll();
    }

    public List<MealCombo> getCombosByMealType(String mealType) {
        try {
            MealCombo.MealType type = MealCombo.MealType.valueOf(mealType.toUpperCase());
            return mealComboRepository.findByMealType(type);
        } catch (IllegalArgumentException e) {
            return List.of(); // Return empty list for invalid meal type
        }
    }
    
    public Optional<MealCombo> findByIdAndMealType(Long id, MealCombo.MealType mealType) {
        return mealComboRepository.findByIdAndMealType(id, mealType);
    }

    public MealCombo save(MealCombo mealCombo) {
        return mealComboRepository.save(mealCombo);
    }

    public MealCombo update(Long id, MealCombo mealCombo) {
        mealCombo.setId(id);
        return mealComboRepository.save(mealCombo);
    }

    public void delete(Long id) {
        mealComboRepository.deleteById(id);
    }
}