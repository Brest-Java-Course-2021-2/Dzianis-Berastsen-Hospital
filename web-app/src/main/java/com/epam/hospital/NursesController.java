package com.epam.hospital;

import com.epam.hospital.model.Nurse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NursesController {

    private static final Logger log = LogManager.getLogger(NursesController.class);
    private final NurseService nurseService;

    @Autowired
    public NursesController(final NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping(value = "/nurses")
    public String nurses(Model model) {
        model.addAttribute("nurses", nurseService.findAll());
        log.info("IN NursesController nurses() find all nurses: {}", model);
        return "nurses";
    }

    @GetMapping(value = "/nurse")
    public String addNursePage(Model model) {
        model.addAttribute("isNew", true)
                .addAttribute("nurse", new Nurse());
        log.info("IN NursesController addNursePage() go to add page: {}", model);
        return "nurse";
    }

    @PostMapping(value = "/nurse")
    public String addNurse(Nurse nurse) {
        nurseService.create(nurse);
        log.info("IN NursesController addNurse() add nurse: {}", nurse);
        return "redirect:/nurses";
    }

    @GetMapping(value = "/nurse/{id}/delete")
    public String deleteNurse(@PathVariable Integer id) {
        nurseService.delete(id);
        log.info("IN NursesController deleteNurse() delete nurse with id: {}", id);
        return "redirect:/nurses";
    }

    @GetMapping(value = "/nurse/{id}")
    public String updateNursePage(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false)
                .addAttribute("nurse", nurseService.findById(id));
        log.info("IN NursesController updateNursePage() go to update page: {}, with id: {}", model, id);
        return "nurse";
    }

    @PostMapping(value = "/nurse/{id}")
    public String updateNurse(Nurse nurse) {
        nurseService.update(nurse);
        log.info("IN NursesController updateNurse() update nurse: {}", nurse);
        return "redirect:/nurses";
    }
}
