package com.example.demo.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.FoodRecord;

@Repository
public interface FoodRecordRepository extends JpaRepository<FoodRecord, Long> {
    List<FoodRecord> findAllFromLastWeek(LocalDateTime starDate, LocalDateTime endDate);
}
