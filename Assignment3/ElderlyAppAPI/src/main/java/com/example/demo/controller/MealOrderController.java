package com.example.demo.controller;

import com.example.demo.model.MealOrder;
import com.example.demo.repository.MealOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mealorders")
public class MealOrderController {

    @Autowired
    private MealOrderRepository mealOrderRepository;

    // Place a meal order
    @PostMapping
    public ResponseEntity<MealOrder> placeMealOrder(@RequestBody MealOrder mealOrder) {
        MealOrder savedOrder = mealOrderRepository.save(mealOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Get all meal orders
    @GetMapping
    public ResponseEntity<List<MealOrder>> getAllMealOrders() {
        List<MealOrder> orders = mealOrderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Cancel a meal order (delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelMealOrder(@PathVariable Long id) {
        if (mealOrderRepository.existsById(id)) {
            mealOrderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
