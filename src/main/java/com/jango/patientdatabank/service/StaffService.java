package com.jango.patientdatabank.service;

import com.jango.patientdatabank.model.Staff;
import com.jango.patientdatabank.pojo.Response;
import com.jango.patientdatabank.pojo.StaffPojo;
import com.jango.patientdatabank.repository.StaffRepository;
import java.time.LocalDate;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StaffService {

  @Autowired
  private StaffRepository staffRepository;


  public Response createStaff(StaffPojo staffPojo) {

    try {
      Staff staff = new ModelMapper().map(staffPojo, Staff.class);
      staff.setId(0L);
      staff.setRegistrationDate(LocalDate.now());
      Staff mStaff = staffRepository.save(staff);
      return new Response.ResponseBuilder<>()
          .message("Saved Successfully")
          .code(200)
          .status(true)
          .data(mStaff)
          .build();

    } catch (Exception e) {
      log.error(e.getMessage());
      return new Response.ResponseBuilder<>()
          .code(500)
          .status(false)
          .message("Error Occurred")
          .build();
    }

  }

  public Response updateStaffProfile(StaffPojo staffPojo) {
    return staffRepository.findByUuid(UUID.fromString(staffPojo.getUuid())).map(staff -> {
      staff.setName(staffPojo.getName());
      staffRepository.save(staff);
      return new Response.ResponseBuilder<>()
          .data(staff)
          .status(true)
          .message("User Edited Successfully")
          .code(200)
          .build();
    }).orElse(
        new Response.ResponseBuilder<>()
            .code(404)
            .status(false)
            .message("UUID provided not found")
            .build()
    );
  }

}
