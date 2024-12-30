package com.example.demo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Food;
import com.example.demo.Repositories.FoodRecordRepository;
import com.example.demo.Repositories.FoodRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodRecordRepository foodRecordRepository;

    public Food create(Food Food) {
        return foodRepository.save(Food);
    }

    public Food update(Food newFood, Long id) {
        return foodRepository.findById(id)
                .map(Food -> {
                    Food.setName(newFood.getName());
                    Food.setSugarLevel(newFood.getSugarLevel());
                    Food.setImages(newFood.getImages());
                    return foodRepository.save(Food);
                })
                .orElseGet(() -> {
                    return foodRepository.save(newFood);
                });
    }

    public Food findById(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if (!food.isPresent()) {
            return null;
        }
        return food.get();
    }

    public Iterable<Food> findAll() {
        return foodRepository.findAll();
    }

    public void removeById(Long id) {
        foodRecordRepository.deleteAllByFood_FoodID(id);

        foodRepository.deleteById(id);
    }

    public void removeAll() {
        foodRepository.deleteAll();
    }
}
