/**
 * 
 */
package com.example.demo.model;

/**
 * 
 */
import jakarta.persistence.*;

@Entity
public class ComboItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    @ManyToOne
    @JoinColumn(name = "meal_combo_id")
    private MealCombo mealCombo; // Link back to MealCombo

    // Constructors, getters, setters...

    public ComboItem() {}

    public ComboItem(String item, MealCombo mealCombo) {
        this.item = item;
        this.mealCombo = mealCombo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public MealCombo getMealCombo() {
        return mealCombo;
    }

    public void setMealCombo(MealCombo mealCombo) {
        this.mealCombo = mealCombo;
    }
}