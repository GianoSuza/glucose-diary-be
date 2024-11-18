package com.example.demo.Models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "FOOD RECORD")
public class FoodRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodRecordId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Food food;

    @NotNull(message = "Date record is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;

    public FoodRecord(Long foodRecordId, User user, Food food,
            @NotNull(message = "Date record is required") Date recordDate) {
        this.foodRecordId = foodRecordId;
        this.user = user;
        this.food = food;
        this.recordDate = recordDate;
    }

    public FoodRecord() {
    }

    public Long getFoodRecordId() {
        return foodRecordId;
    }

    public void setFoodRecordId(Long foodRecordId) {
        this.foodRecordId = foodRecordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
