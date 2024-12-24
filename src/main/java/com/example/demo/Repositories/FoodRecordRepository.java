package com.example.demo.Repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.FoodRecord;

@Repository
public interface FoodRecordRepository extends JpaRepository<FoodRecord, Long> {
    @Query("SELECT f FROM FoodRecord f WHERE f.recordDate >= :startDate")
    List<FoodRecord> findAllWithinOneWeek(Date startDate);
}
