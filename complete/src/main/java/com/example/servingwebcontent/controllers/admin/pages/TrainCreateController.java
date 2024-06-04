package com.example.servingwebcontent.controllers.admin.pages;

import com.example.servingwebcontent.forms.TrainForm;
import com.example.servingwebcontent.repositories.TrainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TrainCreateController {

    private final TrainRepository trainRepository;

    public TrainCreateController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @GetMapping("/trains/create")
    public String index(Model model) {
        model.addAttribute("trains", trainRepository.findAll());
        model.addAttribute("trainForm", new TrainForm());
        return "trains";
    }
}
