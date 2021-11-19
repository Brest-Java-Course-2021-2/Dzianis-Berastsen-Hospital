package com.epam.hospital.mapper;

import com.epam.hospital.model.Nurse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseRowMapper implements RowMapper<Nurse> {

    @Override
    public Nurse mapRow(ResultSet resultSet, int i) throws SQLException {
        Nurse nurse = new Nurse();
        nurse.setId(resultSet.getInt("id"));
        nurse.setFirstName(resultSet.getString("first_name"));
        nurse.setLastName(resultSet.getString("last_name"));
        return nurse;
    }
}