package com.example.demo.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Models.Food;
import com.example.demo.Repositories.FoodRecordRepository;
import com.example.demo.Repositories.FoodRepository;

class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodRecordRepository foodRecordRepository;

    @InjectMocks
    private FoodService foodService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Food food = new Food(null, "Pizza", 10.5, "pizza.jpg");
        Food savedFood = new Food(1L, "Pizza", 10.5, "pizza.jpg");

        when(foodRepository.save(food)).thenReturn(savedFood);

        Food result = foodService.create(food);

        assertNotNull(result);
        assertEquals(savedFood.getfoodID(), result.getfoodID());
        assertEquals(savedFood.getName(), result.getName());
        verify(foodRepository, times(1)).save(food);
    }

    @Test
    void testUpdate() {
        Food existingFood = new Food(1L, "Burger", 15.0, "burger.jpg");
        Food updatedFood = new Food(null, "Sandwich", 8.0, "sandwich.jpg");
        Food savedFood = new Food(1L, "Sandwich", 8.0, "sandwich.jpg");

        when(foodRepository.findById(1L)).thenReturn(Optional.of(existingFood));
        when(foodRepository.save(existingFood)).thenReturn(savedFood);

        Food result = foodService.update(updatedFood, 1L);

        assertNotNull(result);
        assertEquals(savedFood.getName(), result.getName());
        verify(foodRepository, times(1)).findById(1L);
        verify(foodRepository, times(1)).save(existingFood);
    }

    @Test
    void testFindByIdFound() {
        Food food = new Food(1L, "Pizza", 10.5, "pizza.jpg");

        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        Food result = foodService.findById(1L);

        assertNotNull(result);
        assertEquals(food.getName(), result.getName());
        verify(foodRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(foodRepository.findById(1L)).thenReturn(Optional.empty());

        Food result = foodService.findById(1L);

        assertNull(result);
        verify(foodRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        Food food1 = new Food(1L, "Pizza", 10.5, "pizza.jpg");
        Food food2 = new Food(2L, "Burger", 15.0, "burger.jpg");

        when(foodRepository.findAll()).thenReturn(Arrays.asList(food1, food2));

        Iterable<Food> result = foodService.findAll();

        assertNotNull(result);
        assertEquals(2, ((Collection<?>) result).size());
        verify(foodRepository, times(1)).findAll();
    }

    @Test
    void testRemoveById() {
        doNothing().when(foodRecordRepository).deleteAllByFood_FoodID(1L);
        doNothing().when(foodRepository).deleteById(1L);

        foodService.removeById(1L);

        verify(foodRecordRepository, times(1)).deleteAllByFood_FoodID(1L);
        verify(foodRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRemoveAll() {
        doNothing().when(foodRepository).deleteAll();

        foodService.removeAll();

        verify(foodRepository, times(1)).deleteAll();
    }
}
