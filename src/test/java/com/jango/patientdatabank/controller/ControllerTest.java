package com.jango.patientdatabank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jango.patientdatabank.abstracts.AbstractTest;
import com.jango.patientdatabank.model.Staff;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ControllerTest extends AbstractTest {

  @Override
  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void createStaff() throws Exception {
    String uri = "/staff";
    UUID uuid = UUID.randomUUID();
    LocalDateTime date = LocalDateTime.now();
    Staff staff = new Staff();
    staff.setUuid(uuid.toString());
    staff.setName("Test");
    staff.setRegistrationDate(date);
    staff.setId(1L);

    String inputJson = super.mapToJson(staff);
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
//    String content = mvcResult.getResponse().getContentAsString();
//    assertEquals(content, "Saved Successfully");
  }

}
