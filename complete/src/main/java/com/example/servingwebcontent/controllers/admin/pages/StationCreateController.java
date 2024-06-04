package com.example.servingwebcontent.controllers.admin.pages;

import com.example.servingwebcontent.forms.StationForm;
import com.example.servingwebcontent.repositories.StationsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class StationCreateController {

    private final StationsRepository stationsRepository;

    public StationCreateController(StationsRepository stationsRepository) {
        this.stationsRepository = stationsRepository;
    }

    @GetMapping("/stations/create")
    public String index(Model model) {
        model.addAttribute("stations", stationsRepository.findAll());
        model.addAttribute("stationForm", new StationForm());
        return "stations";
    }
}
