package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.BookDoctor;

@Repository
public interface BookDoctorRepository extends JpaRepository<BookDoctor, Long> {
    // @Modifying
    // @Query("DELETE FROM BookDoctor b WHERE b.user_user_id = :userId")
    void deleteAllByUser_UserId(Long userId);

    // @Modifying
    // @Query("DELETE FROM BookDoctor b WHERE b.doctor_doctor_id = :doctorId")
    void deleteAllByDoctor_DoctorId(Long doctorId);
}
