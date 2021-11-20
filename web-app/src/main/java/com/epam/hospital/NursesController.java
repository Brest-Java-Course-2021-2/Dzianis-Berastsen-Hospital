package com.epam.hospital;

import com.epam.hospital.model.Nurse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping(value = "/nurse")
    public String addNursePage(Model model) {
        model.addAttribute("isNew", true)
                .addAttribute("nurse", new Nurse());
        return "nurse";
    }

    @PostMapping(value = "/nurse")
    public String addNurse(Nurse nurse) {
        nurseService.create(nurse);
        return "redirect:/nurses";
    }

    @GetMapping(value = "/nurse/{id}/delete")
    public String deleteNurse(@PathVariable Integer id) {
        nurseService.delete(id);
        return "redirect:/nurses";
    }

    @GetMapping(value = "/nurse/{id}")
    public String updateNursePage(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false)
                .addAttribute("nurse", nurseService.findById(id));
        return "nurse";
    }

    @PostMapping(value = "/nurse/{id}")
    public String updateNurse(Nurse nurse) {
        nurseService.update(nurse);
        return "redirect:/nurses";
    }
}
