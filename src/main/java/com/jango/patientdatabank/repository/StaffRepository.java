package com.jango.patientdatabank.repository;

import com.jango.patientdatabank.model.Staff;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

  Optional<Staff> findByUuid(String uuid);
}
