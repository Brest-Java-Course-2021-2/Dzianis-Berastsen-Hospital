package com.epam.hospital;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NursesController {

    private final NurseService nurseService;

    public NursesController(final NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping(value = "/nurses")
    public String nurses(Model model) {
        model.addAttribute("nurses", nurseService.findAll());
        return "nurses";
    }
}
