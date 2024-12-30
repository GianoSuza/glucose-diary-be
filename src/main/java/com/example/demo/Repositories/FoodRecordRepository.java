package com.example.demo.Repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.FoodRecord;

@Repository
public interface FoodRecordRepository extends JpaRepository<FoodRecord, Long> {
    // @Query("DELETE FROM FoodRecord f WHERE f.user_user_id = :userId")
    void deleteAllByUser_UserId(Long userId);

    // @Query("DELETE FROM FoodRecord f WHERE f.food_foodid = :foodId")
    void deleteAllByFood_FoodID(Long foodId);

    @Query("SELECT f FROM FoodRecord f WHERE f.recordDate >= :startDate")
    List<FoodRecord> findAllWithinOneWeek(Date startDate);
}
