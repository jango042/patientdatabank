package com.jango.patientdatabank.util;

import com.jango.patientdatabank.repository.StaffRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utill {

  @Autowired
  private  StaffRepository staffRepository;

  public  boolean checkSfaffByUUID(String uuid) {
    return staffRepository.findByUuid(UUID.fromString(uuid)).isPresent();
  }

}
