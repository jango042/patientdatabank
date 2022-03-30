package com.jango.patientdatabank.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Staff {


  private Long id;
  @Id
  private String uuid;
  @Column(name = "registration_date")
  private LocalDateTime registrationDate;
  private String name;

}
