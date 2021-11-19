package com.epam.hospital;

import com.epam.hospital.model.Nurse;

import java.util.List;
import java.util.Optional;

public interface NurseService {

    List<Nurse> findAll();

    Integer create(Nurse nurse);

    Integer update(Nurse nurse);

    Integer delete(Integer nurseId);

    Optional<Nurse> findById(Integer nurseId);
}
