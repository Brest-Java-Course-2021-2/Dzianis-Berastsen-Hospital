package com.epam.hospital.dao;

import com.epam.hospital.PatientDao;
import com.epam.hospital.model.Patient;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class PatientDaoJDBCImpl implements PatientDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PatientDaoJDBCImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Override
    public Integer create(Patient patient) {
        return null;
    }

    @Override
    public Integer update(Patient patient) {
        return null;
    }

    @Override
    public Integer delete(Integer patientId) {
        return null;
    }
}
