package com.jango.patientdatabank.service;

import com.jango.patientdatabank.model.Staff;
import com.jango.patientdatabank.pojo.Response;
import com.jango.patientdatabank.pojo.StaffPojo;
import com.jango.patientdatabank.pojo.StaffResponse;
import com.jango.patientdatabank.pojo.StaffUpdatePojo;
import com.jango.patientdatabank.repository.StaffRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StaffService {

  @Autowired
  private StaffRepository staffRepository;


  public ResponseEntity<Response>  createStaff(StaffPojo staffPojo) {



    try {
      Staff staff = new ModelMapper().map(staffPojo, Staff.class);
      staff.setId(staffPojo.getId());
      staff.setUuid(UUID.randomUUID().toString());
      staff.setRegistrationDate(LocalDateTime.now());
      staffRepository.save(staff);

      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .message("Saved Successfully")
          .code(201)
          .build(), HttpStatus.CREATED ) ;

    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .message("User Edited Successfully")
          .code(500)
          .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  public ResponseEntity<Response> updateStaffProfile(StaffUpdatePojo staffPojo) {
    return staffRepository.findByUuid(staffPojo.getUuid()).map(staff -> {
      staff.setName(staffPojo.getName());
      staff.setId(staffPojo.getId());
      StaffResponse mStaff = new ModelMapper().map(staffRepository.save(staff), StaffResponse.class);

      return new ResponseEntity<Response>(new Response.ResponseBuilder<>()
          .data(mStaff)
          .message("User Edited Successfully")
          .code(200)
          .build(), HttpStatus.OK);

    }).orElse(
       new ResponseEntity<Response>(new Response.ResponseBuilder<>()
            .message("User Edited Successfully")
            .code(404)
            .build(), HttpStatus.NOT_FOUND)

    );
  }

}
