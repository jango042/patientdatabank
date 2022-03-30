package com.jango.patientdatabank.service;

import com.jango.patientdatabank.model.Patient;
import com.jango.patientdatabank.model.Staff;
import com.jango.patientdatabank.pojo.PatientResponse;
import com.jango.patientdatabank.pojo.Response;
import com.jango.patientdatabank.repository.PatientRepository;
import com.jango.patientdatabank.repository.StaffRepository;
import com.jango.patientdatabank.util.Utill;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  public ResponseEntity<Response> findByAgeEqualTwoYears(String uuid, int age) {

    log.info("UUid::::{}", uuid);
    if (staffRepository == null) {
      log.info("Staff Repo is null");
    } else {
      log.info("Staff Repo is not null");
    }

    if ( utill.checkSfaffByUUID(uuid)) {
      List<PatientResponse> patientResponseList = new ArrayList<>();
      for (Patient pt : patientRepository.findByAgeGreaterThanEqual(age)) {
        PatientResponse patientResponse = new ModelMapper().map(pt, PatientResponse.class);
        patientResponseList.add(patientResponse);
      }
      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .data(patientResponseList)
          .message("Patient Records")
          .code(200)
          .build(), HttpStatus.OK ) ;

    } else {
      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .message("UUID provided not found")
          .code(404)
          .build(), HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Response> deletePatientsBetweenTwoAgeRange(String uuid, int age1, int age2) {

    if (utill.checkSfaffByUUID(uuid)) {
//      List<Patient> patientList = patientRepository.findByAgeBetween(age1, age2);
      patientRepository.deleteAll(patientRepository.findByAgeBetween(age1, age2));
      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .message("Patient Records Deleted Successfully")
          .code(200)
          .build(), HttpStatus.OK ) ;

    } else {
      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .message("UUID provided not found")
          .code(404)
          .build(), HttpStatus.NOT_FOUND);
    }
  }

}
