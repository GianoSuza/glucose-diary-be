package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Doctor;
import com.example.demo.Repositories.BookDoctorRepository;
import com.example.demo.Repositories.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private BookDoctorRepository bookDoctorRepository;

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor update(Doctor newDoctor, Long id) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setName(newDoctor.getName());
                    doctor.setEmail(newDoctor.getEmail());
                    doctor.setSpeciality(newDoctor.getSpeciality());
                    doctor.setPracticeDay(newDoctor.getPracticeDay());
                    doctor.setImages(newDoctor.getImages());
                    return doctorRepository.save(doctor);
                })
                .orElseGet(() -> {
                    return doctorRepository.save(newDoctor);
                });
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).get();
    }

    public Iterable<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public void removeById(Long id) {
        bookDoctorRepository.deleteAllByDoctor_DoctorId(id);

        doctorRepository.deleteById(id);
    }

    public void removeAll() {
        doctorRepository.deleteAll();
    }
}
