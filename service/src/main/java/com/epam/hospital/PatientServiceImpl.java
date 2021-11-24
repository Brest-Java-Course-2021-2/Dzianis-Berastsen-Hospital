package com.epam.hospital;

import com.epam.hospital.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    private static final Logger log = LogManager.getLogger(PatientServiceImpl.class);
    private final PatientDao patientDao;

    public PatientServiceImpl(final PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public List<Patient> getAll() {
        log.info("IN PatientServiceImpl getAll()");
        return patientDao.findAll();
    }

    @Override
    public Integer create(Patient patient) {
        patient.setIllnessDate(LocalDate.now());
        log.info("IN PatientServiceImpl create() create patient: {}", patient);
        return patientDao.create(patient);
    }

    @Override
    public Integer update(Patient patient) {
        log.info("IN PatientServiceImpl update() update patient: {}", patient);
        return patientDao.update(patient);
    }

    @Override
    public Integer delete(Integer patientId) {
        log.info("IN PatientServiceImpl delete() delete patient with id: {}", patientId);
        return patientDao.delete(patientId);
    }

    @Override
    public Optional<Patient> findById(Integer patientId) {
        log.info("IN PatientServiceImpl findById() find patient with id: {}", patientId);
        return patientDao.findById(patientId);
    }
}
