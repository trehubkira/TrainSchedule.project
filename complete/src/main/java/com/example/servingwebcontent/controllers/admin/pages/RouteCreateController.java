package com.example.servingwebcontent.controllers.admin.pages;

import com.example.servingwebcontent.forms.RouteForm;
import com.example.servingwebcontent.repositories.RouteRepository;
import com.example.servingwebcontent.repositories.StationsRepository;
import com.example.servingwebcontent.repositories.TrainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RouteCreateController {

    private final RouteRepository routeRepository;
    private final TrainRepository trainRepository;
    private final StationsRepository stationsRepository;

    public RouteCreateController(
            RouteRepository routeRepository,
            TrainRepository trainRepository,
            StationsRepository stationsRepository
    ) {
        this.routeRepository = routeRepository;
        this.trainRepository = trainRepository;
        this.stationsRepository = stationsRepository;
    }

    @GetMapping("/routes/create")
    public String index(Model model) {
        model.addAttribute("routeForm", new RouteForm());
        model.addAttribute("stations", stationsRepository.findAll());
        model.addAttribute("routes", routeRepository.findAll());
        model.addAttribute("trains", trainRepository.findAll());
        return "routes";
    }
}
