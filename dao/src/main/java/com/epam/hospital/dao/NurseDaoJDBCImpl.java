package com.epam.hospital.dao;

import com.epam.hospital.NurseDao;
import com.epam.hospital.mapper.NurseRowMapper;
import com.epam.hospital.model.Nurse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NurseDaoJDBCImpl implements NurseDao {

    private static final Logger log = LogManager.getLogger(NurseDaoJDBCImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final NurseRowMapper nurseRowMapper;

    private static final String SQL_ALL_NURSES = "SELECT * FROM NURSE";
    private static final String SQL_CREATE_NURSE = "INSERT INTO NURSE(first_name,last_name) values(:firstName,:lastName)";
    private static final String SQL_UPDATE_NURSE = "UPDATE NURSE SET first_name = :firstName, last_name = :lastName where id = :id";
    private static final String SQL_DELETE_NURSE = "UPDATE patient SET nurse_id = NULL where nurse_id = :id; DELETE FROM NURSE WHERE id=:id";
    private static final String SQL_FIND_NURSE_BY_ID = "SELECT * FROM NURSE WHERE id=:id";

    @Autowired
    public NurseDaoJDBCImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                            final NurseRowMapper nurseRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.nurseRowMapper = nurseRowMapper;
    }

    @Override
    public List<Nurse> findAll() {
        log.info("IN NurseDaoJDBCImpl findAll()");
        return namedParameterJdbcTemplate.query(SQL_ALL_NURSES, nurseRowMapper);
    }

    @Override
    public Integer create(Nurse nurse) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", nurse.getFirstName())
                .addValue("lastName", nurse.getLastName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_NURSE, sqlParameterSource, keyHolder);
        log.info("IN NurseDaoJDBCImpl create() create nurse:{}", nurse);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Nurse nurse) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", nurse.getFirstName())
                .addValue("lastName", nurse.getLastName())
                .addValue("id", nurse.getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_UPDATE_NURSE, sqlParameterSource, keyHolder);
        log.info("IN NurseDaoJDBCImpl update() update nurse: {} with id: {}", nurse, nurse.getId());
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer delete(Integer nurseId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", nurseId);
        namedParameterJdbcTemplate.update(SQL_DELETE_NURSE, sqlParameterSource);
        log.info("IN NurseDaoJDBCImpl delete() delete nurse with id: {}", nurseId);
        return nurseId;
    }

    @Override
    public Optional<Nurse> findById(Integer nurseId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", nurseId);
        Nurse nurse;
        try {
            nurse = namedParameterJdbcTemplate.queryForObject(SQL_FIND_NURSE_BY_ID, sqlParameterSource, nurseRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
        log.info("IN NurseDaoJDBCImpl findById() find nurse {} with id: {}",nurse, nurseId);
        return Optional.ofNullable(nurse);
    }
}
