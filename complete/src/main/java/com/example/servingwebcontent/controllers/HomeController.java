package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.Route;
import com.example.servingwebcontent.models.Station;
import com.example.servingwebcontent.repositories.RouteRepository;
import com.example.servingwebcontent.repositories.StationsRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final RouteRepository routeRepository;
    private final StationsRepository stationsRepository;

    public HomeController(
            RouteRepository routeRepository,
            StationsRepository stationsRepository
    ) {
        this.routeRepository = routeRepository;
        this.stationsRepository = stationsRepository;
    }

    @PostMapping
    public String findRoutes(@RequestParam(value = "departureStation", required = false)
                             Long departureStationId,
                             @RequestParam(value = "arrivalStation", required = false)
                             Long arrivalStationId,
                             @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             Model model) {

        model.addAttribute("stations", stationsRepository.findAll());

        if (departureStationId == null && arrivalStationId == null) {
            model.addAttribute("routes", routeRepository.findAll());
            return "index";
        }

        Station departureStation = stationsRepository.findById(departureStationId).orElse(null);
        Station arrivalStation = stationsRepository.findById(arrivalStationId).orElse(null);

        List<Route> routes = routeRepository.findAllByDepartureStationAndArrivalStationAndStart(departureStation, arrivalStation, date);

        if (routes.isEmpty()) {
            model.addAttribute("message", "Потягів не знайдено");
        }

        model.addAttribute("routes", routes);

        return "index";
    }

    @GetMapping
    public String findRoutes(Model model) {
        model.addAttribute("routes", routeRepository.findAll());
        model.addAttribute("stations", stationsRepository.findAll());

        return "index";
    }
}
