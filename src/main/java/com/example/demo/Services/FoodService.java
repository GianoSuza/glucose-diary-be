package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Food;
import com.example.demo.Repositories.FoodRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Food create(Food Food) {
        return foodRepository.save(Food);
    }

    public Food update(Food newFood, Long id) {
        return foodRepository.findById(id)
                .map(Food -> {
                    Food.setName(newFood.getName());
                    Food.setSugarLevel(newFood.getSugarLevel());
                    return foodRepository.save(Food);
                })
                .orElseGet(() -> {
                    return foodRepository.save(newFood);
                });
    }

    public Food findById(Long id) {
        return foodRepository.findById(id).get();
    }

    public Iterable<Food> findAll() {
        return foodRepository.findAll();
    }

    public void removeById(Long id) {
        foodRepository.deleteById(id);
    }

    public void removeAll() {
        foodRepository.deleteAll();
    }
}
