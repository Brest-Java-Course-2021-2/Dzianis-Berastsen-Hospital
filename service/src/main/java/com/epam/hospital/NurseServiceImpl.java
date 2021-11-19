package com.epam.hospital;

import com.epam.hospital.model.Nurse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {

    private final NurseDao nurseDao;
    private static final Logger log = LogManager.getLogger(NurseServiceImpl.class);

    public NurseServiceImpl(final NurseDao nurseDao) {
        this.nurseDao = nurseDao;
    }

    @Override
    public List<Nurse> findAll() {
        log.info("IN NurseServiceImpl findAll()");
        return nurseDao.findAll();
    }

    @Override
    public Integer create(Nurse nurse) {
        log.info("IN NurseServiceImpl create() nurse: {}", nurse);
        return nurseDao.create(nurse);
    }

    @Override
    public Integer update(Nurse nurse) {
        log.info("IN NurseServiceImpl update() update nurse with id: {}", nurse.getId());
        return nurseDao.update(nurse);
    }

    @Override
    public Integer delete(Integer nurseId) {
        log.info("IN NurseServiceImpl delete() delete nurse with id: {}", nurseId);
        return nurseDao.delete(nurseId);
    }

    @Override
    public Optional<Nurse> findById(Integer nurseId) {
        log.info("IN NurseServiceImpl findById() find nurse with id: {}", nurseId);
        return nurseDao.findById(nurseId);
    }
}
