package com.example.servingwebcontent.controllers.admin.api;

import com.example.servingwebcontent.forms.RouteForm;
import com.example.servingwebcontent.models.Route;
import com.example.servingwebcontent.repositories.RouteRepository;
import com.example.servingwebcontent.repositories.StationsRepository;
import com.example.servingwebcontent.repositories.TrainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/api/")
public class RouteApiController {

    private final RouteRepository routeRepository;
    private final StationsRepository stationRepository;
    private final TrainRepository trainRepository;

    public RouteApiController(RouteRepository routeRepository, StationsRepository stationRepository, TrainRepository trainRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.trainRepository = trainRepository;
    }

    @PostMapping("/routes/create")
    public RedirectView createRoute(@ModelAttribute RouteForm routeForm) {

        Route route = new Route();
        route.setDepartureStation(stationRepository.findById(routeForm.getDepartureStationId()).orElseThrow());
        route.setArrivalStation(stationRepository.findById(routeForm.getArrivalStationId()).orElseThrow());
        route.setTrain(trainRepository.findById(routeForm.getTrainId()).orElseThrow());
        route.setStart(routeForm.getStart());
        route.setEnd(routeForm.getEnd());
        routeRepository.save(route);

        return new RedirectView("/admin/routes/create");
    }

    @PostMapping("/routes/delete/{id}")
    public RedirectView deleteTrain(@PathVariable("id") Long id) {
        routeRepository.deleteById(id);
        return new RedirectView("/admin/routes/create");
    }

    @PostMapping("/routes/update/{id}")
    public RedirectView updateRoute(@PathVariable("id") Long id, @ModelAttribute RouteForm routeForm) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid route Id:" + id));

        route.setDepartureStation(stationRepository.findById(routeForm.getDepartureStationId()).orElse(null));
        route.setArrivalStation(stationRepository.findById(routeForm.getArrivalStationId()).orElse(null));
        route.setTrain(trainRepository.findById(routeForm.getTrainId()).orElse(null));
        route.setStart(routeForm.getStart());
        route.setEnd(routeForm.getEnd());

        routeRepository.save(route);

        return new RedirectView("/admin/routes/create");
    }
}
