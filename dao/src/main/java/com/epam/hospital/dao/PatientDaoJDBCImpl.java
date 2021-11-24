package com.epam.hospital.dao;

import com.epam.hospital.PatientDao;
import com.epam.hospital.mapper.PatientRowMapper;
import com.epam.hospital.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class PatientDaoJDBCImpl implements PatientDao {

    private static final Logger log = LogManager.getLogger(PatientDaoJDBCImpl.class);
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PatientRowMapper patientRowMapper;

    private static final String SQL_ALL_PATIENTS = "SELECT * FROM patient";
    private static final String SQL_CREATE_PATIENT = "INSERT INTO patient (first_name, last_name, diagnosis, illness_date, nurse_id)" +
            "VALUES(:firstName, :lastName, :diagnosis, :illnessDate, :nurseId)";
    private static final String SQL_UPDATE_PATIENT = "UPDATE patient SET first_name=:firstName, last_name=:lastName," +
            "diagnosis=:diagnosis, nurse_id=:nurseId WHERE id=:id";
    private static final String SQL_DELETE_PATIENT = "DELETE FROM patient WHERE id=:id";
    private static final String SQL_FIND_PATIENT_BY_ID = "SELECT * FROM patient WHERE id=:id";

    public PatientDaoJDBCImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              final PatientRowMapper patientRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.patientRowMapper = patientRowMapper;
    }

    @Override
    public List<Patient> findAll() {
        log.info("IN PatientDaoJDBCImpl findAll()");
        return namedParameterJdbcTemplate.query(SQL_ALL_PATIENTS, patientRowMapper);
    }

    @Override
    public Integer create(Patient patient) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", patient.getFirstName())
                .addValue("lastName", patient.getLastName())
                .addValue("diagnosis", patient.getDiagnosis())
                .addValue("illnessDate", patient.getIllnessDate())
                .addValue("nurseId", patient.getNursesId());
        namedParameterJdbcTemplate.update(SQL_CREATE_PATIENT, sqlParameterSource, keyHolder);
        log.info("IN PatientDaoJDBCImpl create() create patient: {}", patient);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Patient patient) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", patient.getFirstName())
                .addValue("lastName", patient.getLastName())
                .addValue("diagnosis", patient.getDiagnosis())
                .addValue("nurseId", patient.getNursesId())
                .addValue("id", patient.getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_PATIENT, sqlParameterSource, keyHolder);
        log.info("IN PatientDaoJDBCImpl update() update patient: {} with id: {}",patient, patient.getId());
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer delete(Integer patientId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", patientId);
        namedParameterJdbcTemplate.update(SQL_DELETE_PATIENT, sqlParameterSource);
        log.info("IN PatientDaoJDBCImpl delete() delete patient with id: {}", patientId);
        return patientId;
    }

    @Override
    public Optional<Patient> findById(Integer patientId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", patientId);
        Patient patient;
        try {
            patient = namedParameterJdbcTemplate.queryForObject(SQL_FIND_PATIENT_BY_ID, sqlParameterSource, patientRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
        log.info("IN PatientDaoJDBCImpl findById() find patient: {} with id: {}", patient, patientId);
        return Optional.ofNullable(patient);
    }
}
