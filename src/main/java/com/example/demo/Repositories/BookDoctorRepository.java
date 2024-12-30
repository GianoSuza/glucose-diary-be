package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.BookDoctor;

@Repository
public interface BookDoctorRepository extends JpaRepository<BookDoctor, Long> {
    @Query("DELETE FROM BookDoctor f WHERE f.user_user_id = :userId")
    void deleteAllByUserId(Long userId);

    @Query("DELETE FROM BookDoctor f WHERE f.doctor_doctor_id = :doctorId")
    void deleteAllByDoctorId(Long doctorId);
}
