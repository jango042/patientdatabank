package com.jango.patientdatabank.service;

import com.jango.patientdatabank.model.Patient;
import com.jango.patientdatabank.model.Staff;
import com.jango.patientdatabank.pojo.Response;
import com.jango.patientdatabank.repository.PatientRepository;
import com.jango.patientdatabank.repository.StaffRepository;
import com.jango.patientdatabank.util.Utill;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private StaffRepository staffRepository;

  @Autowired
  private Utill utill;

  public Response findByAgeEqualTwoYears(String uuid, int age) {

    log.info("UUid::::{}", uuid);
    if (staffRepository == null) {
      log.info("Staff Repo is null");
    } else {
      log.info("Staff Repo is not null");
    }

    if ( utill.checkSfaffByUUID(uuid)) {
      return new Response.ResponseBuilder<>()
          .data(patientRepository.findByAgeGreaterThanEqual(age))
          .status(true)
          .message("Patient Records")
          .code(200)
          .build();
    } else {
      return new Response.ResponseBuilder<>()
          .code(404)
          .status(false)
          .message("UUID provided not found")
          .build();
    }
  }

  public Response deletePatientsBetweenTwoAgeRange(String uuid, int age1, int age2) {

    if (utill.checkSfaffByUUID(uuid)) {
//      List<Patient> patientList = patientRepository.findByAgeBetween(age1, age2);
      patientRepository.deleteAll(patientRepository.findByAgeBetween(age1, age2));
      return new Response.ResponseBuilder<>()
          .status(true)
          .message("Patient Records Deleted Successfully")
          .code(200)
          .build();

    } else {
      return new Response.ResponseBuilder<>()
          .code(404)
          .status(false)
          .message("UUID provided not found")
          .build();
    }
  }

}
