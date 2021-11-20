package com.epam.hospital;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientsController {

    @GetMapping(value = "/patients")
    public String patients(Model model) {
        return "patients";
    }

    @GetMapping(value = "/patient")
    public String patient(Model model) {
        return "patient";
    }
}
