package com.jango.patientdatabank.util;

import com.jango.patientdatabank.model.Patient;
import com.jango.patientdatabank.repository.PatientRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CsvHelper {

  @Autowired
  private Utill utill;

  @Autowired
  private PatientRepository patientRepository;


  public void writePatientProfileToCsv(Writer writer, String uuid, Long patientId) {

    if (utill.checkSfaffByUUID(uuid)) {
      Optional<Patient> patient = patientRepository.findById(patientId);
      if (patient.isPresent()) {
        log.info("Local Date::::"+ patient.get().getLastVisitDate());
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
        .withHeader("ID", "Age", "Patient Name", "Last Visit Date"))) {
          csvPrinter
              .printRecord(patient.get().getId(), patient.get().getAge(), patient.get().getName(), patient.get().getLastVisitDate().toString());
        } catch (IOException e) {
          log.error("Error While writing CSV ", e);
        }
      } else {
        log.error("Patient Id provided not found ");
      }

    } else {
      log.error("Staff Id provided not found ");
    }

  }

}
