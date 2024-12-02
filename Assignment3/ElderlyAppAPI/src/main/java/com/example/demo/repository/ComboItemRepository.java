package com.example.demo.repository;

import com.example.demo.model.ComboItem;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboItemRepository extends JpaRepository<ComboItem, Long> {
    List<ComboItem> findByMealComboId(Long mealComboId);
}
