package com.epam.hospital;

import com.epam.hospital.model.Nurse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class NurseServiceImplIT {

    private static final Logger log = LogManager.getLogger(NurseServiceImplIT.class);
    private final NurseServiceImpl nurseService;

    public NurseServiceImplIT(@Autowired NurseServiceImpl nurseService) {
        this.nurseService = nurseService;
    }
    //TODO refactor test logic
    @Test
    void findAll() {
        log.info("IN NurseServiceImplIT execute test findAll()");
        assertNotNull(nurseService);
        assertNotNull(nurseService.findAll());
    }

    @Test
    void create() {
        log.info("IN NurseServiceImplIT execute test create()");
        assertNotNull(nurseService);
        int nursesSizeBefore = nurseService.findAll().size();
        Nurse nurse = new Nurse(5,"Tatsiana", "Levchuk");
        Integer nurseId = nurseService.create(nurse);
        assertNotNull(nurseId);
        assertEquals(nursesSizeBefore, nurseService.findAll().size()-1);
    }

    @Test
    void update() {
        log.info("IN NurseServiceImplIT execute test update()");
        assertNotNull(nurseService);
        Nurse foundNurse = nurseService.findById(1).get();
        Nurse nurse = new Nurse(1,"Ekaterina", "Ivanova");
        nurseService.update(nurse);
        Nurse updatedNurse = nurseService.findById(1).get();
        assertNotEquals(foundNurse, updatedNurse);
    }

    @Test
    void delete() {
        log.info("IN NurseServiceImplIT execute test delete()");
        assertNotNull(nurseService);
        //TODO realize with join table patient
    }

    @Test
    void findById() {
        log.info("IN NurseServiceImplIT execute test findById()");
        assertNotNull(nurseService);
        assertNotNull(nurseService.findById(1));
    }
}