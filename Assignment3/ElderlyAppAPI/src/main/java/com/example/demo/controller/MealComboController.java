package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.MealCombo;
import com.example.demo.service.MealComboService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/meal") // Base URL for the controller
public class MealComboController {

    private final MealComboService mealComboService;

    public MealComboController(MealComboService mealComboService) {
        this.mealComboService = mealComboService;
    }

    // Get all meal combos
    @GetMapping
    public ResponseEntity<List<MealCombo>> getAllMealCombos() {
        List<MealCombo> mealCombos = mealComboService.findAll();
        return new ResponseEntity<>(mealCombos, HttpStatus.OK);
    }

    @GetMapping("/{mealType}")
    public ResponseEntity<List<MealCombo>> getCombosByMealType(@PathVariable String mealType) {
        List<MealCombo> mealCombos = mealComboService.getCombosByMealType(mealType);
        return new ResponseEntity<>(mealCombos, HttpStatus.OK);
    }
    
 // Get Meal Combo by MealType and ID
    @GetMapping("/{mealType}/{id}")
    public ResponseEntity<MealCombo> getComboById(@PathVariable String mealType, @PathVariable Long id) {
        try {
            MealCombo.MealType type = MealCombo.MealType.valueOf(mealType.toUpperCase());
            Optional<MealCombo> mealCombo = mealComboService.findByIdAndMealType(id, type);

            return mealCombo
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Invalid mealType
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<List<MealCombo>> testConnection() {
        List<MealCombo> combos = mealComboService.findAll();
        return new ResponseEntity<>(combos, HttpStatus.OK);
    }

    // Add a new meal combo
    @PostMapping
    public ResponseEntity<MealCombo> createMealCombo(@RequestBody MealCombo mealCombo) {
        MealCombo createdMealCombo = mealComboService.save(mealCombo);
        return new ResponseEntity<>(createdMealCombo, HttpStatus.CREATED);
    }

    // Update an existing meal combo
    @PutMapping("/{id}")
    public ResponseEntity<MealCombo> updateMealCombo(@PathVariable Long id, @RequestBody MealCombo mealCombo) {
        MealCombo updatedMealCombo = mealComboService.update(id, mealCombo);
        return new ResponseEntity<>(updatedMealCombo, HttpStatus.OK);
    }

    // Delete a meal combo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealCombo(@PathVariable Long id) {
        mealComboService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}