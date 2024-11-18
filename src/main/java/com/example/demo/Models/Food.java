package com.example.demo.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "FOOD")
public class Food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodID;

    @NotEmpty(message = "Food name is required")
    @Column(length = 100)
    private String name;

    @NotNull(message = "Sugar level is required")
    private double sugarLevel;

    public Food(Long foodID, @NotEmpty(message = "Food name is required") String name,
            @NotEmpty(message = "Sugar level is required") double sugarLevel) {
        this.foodID = foodID;
        this.name = name;
        this.sugarLevel = sugarLevel;
    }

    public Food() {
    }

    public Long getFoodID() {
        return foodID;
    }

    public void setFoodID(Long foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(double sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

}
