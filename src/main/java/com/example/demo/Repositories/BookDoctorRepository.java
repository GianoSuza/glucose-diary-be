package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.BookDoctor;

@Repository
public interface BookDoctorRepository extends JpaRepository<BookDoctor, Long> {
    @Query("DELETE FROM BookDoctor f WHERE f.user.userId = :userId")
    void deleteAllByUserId(Long userId);

    @Query("DELETE FROM BookDoctor f WHERE f.doctor.doctorId = :doctorId")
    void deleteAllByDoctorId(Long doctorId);
}
