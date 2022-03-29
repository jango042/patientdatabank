package com.jango.patientdatabank.repository;

import com.jango.patientdatabank.model.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  List<Patient> findByAgeGreaterThanEqual(int age);
  List<Patient> findByAgeBetween(int age1, int age2);
}
