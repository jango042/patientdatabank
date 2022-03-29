package com.jango.patientdatabank.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
public class Staff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull(message = "UUID field cannot be empty")
  @Column(name = "uuid",unique = true)
  @Type(type="uuid-char")
  private UUID uuid = UUID.randomUUID();
  @Column(name = "registration_date")
  private LocalDate registrationDate;
  private String name;

}
