package com.epam.hospital;

import com.epam.hospital.model.Patient;

import java.util.List;

public interface PatientDao {
    List<Patient> findAll();

    Integer create(Patient patient);

    Integer update(Patient patient);

    Integer delete(Integer patientId);
}
