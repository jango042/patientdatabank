package com.jango.patientdatabank.controller;

import com.jango.patientdatabank.pojo.Response;
import com.jango.patientdatabank.pojo.StaffPojo;
import com.jango.patientdatabank.service.PatientService;
import com.jango.patientdatabank.service.StaffService;
import com.jango.patientdatabank.util.CsvHelper;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StaffController {

  @Autowired
  private StaffService staffService;

  @Autowired
  private PatientService patientService;

  @Autowired
  private CsvHelper csvHelper;



  @ApiOperation(value = "Create Staff", notes = "Create Staff")
  @PostMapping("/staff")
  public ResponseEntity<Response> createStaff(@RequestBody StaffPojo staffPojo) {

    Response response = staffService.createStaff(staffPojo);
    if (!response.getStatus()) {
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

  @ApiOperation(value = "Update Staff Member Profile", notes = "Update Staff Member profile")
  @PutMapping("/staff")
  public ResponseEntity<Response> updateStaffProfile(@RequestBody StaffPojo staffPojo) {
    Response response = staffService.updateStaffProfile(staffPojo);
    if (!response.getStatus()) {
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

  @ApiOperation(value = "Fetch Patient Profile up to 2 years", notes = "Fetch Patient Profile up to 2 years")
  @GetMapping("/staff/{uuid}/patient/{age}")
  public ResponseEntity<Response> findPatienceUptoTwoYears(@PathVariable("uuid") String uuid, @PathVariable("age") int age) {

    Response response = patientService.findByAgeEqualTwoYears(uuid, age);
    if (!response.getStatus()) {
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

  @ApiOperation(value = "Download Patient Profile via csv", notes = "Download Patient Profile via csv")
  @GetMapping(path = "/staff/{uuid}/patient/{patientId}/download")
  public void getAllEmployeesInCsv(HttpServletResponse servletResponse, @PathVariable("uuid") String uuid, @PathVariable("patientId") Long patientId) throws IOException {
    servletResponse.setContentType("text/csv");
    servletResponse.addHeader("Content-Disposition","attachment; filename=\"patient.csv\"");
    csvHelper.writeEmployeesToCsv(servletResponse.getWriter(), uuid, patientId);
  }

  @ApiOperation(value = "Delete list of Patients between two age range", notes = "Delete list of Patients between two age range")
  @DeleteMapping("/staff/{uuid}/patient/between/{age1}/{age2}")
  public ResponseEntity<Response> deletePatientsProfileBetweenAgeRange(@PathVariable("uuid") String uuid, @PathVariable("age1") int age1, @PathVariable("age2") int age2) {

    Response response = patientService.deletePatientsBetweenTwoAgeRange(uuid,age1,age2);
    if (!response.getStatus()) {
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

}
