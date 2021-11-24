package com.epam.hospital;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientsController {

    private static final Logger log = LogManager.getLogger(PatientsController.class);
    private final PatientService patientService;

    public PatientsController(final PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.getAll());
        log.info("IN PatientsController patients() to do patients: {}", model);
        return "patients";
    }

    @GetMapping(value = "/patient")
    public String patient(Model model) {
        return "patient";
    }
}
