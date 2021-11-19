package com.epam.hospital;

import com.epam.hospital.model.Nurse;

import java.util.List;
import java.util.Optional;

//TODO can be extended from generic crudDao
public interface NurseDao {
    List<Nurse> findAll();

    Integer create(Nurse nurse);

    Integer update(Nurse nurse);

    Integer delete(Integer nurseId);

    Optional<Nurse> findById(Integer nurseId);

}
