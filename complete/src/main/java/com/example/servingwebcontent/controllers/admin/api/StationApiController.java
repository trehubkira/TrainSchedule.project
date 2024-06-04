package com.example.servingwebcontent.controllers.admin.api;

import com.example.servingwebcontent.forms.StationForm;
import com.example.servingwebcontent.models.Route;
import com.example.servingwebcontent.models.Station;
import com.example.servingwebcontent.repositories.RouteRepository;
import com.example.servingwebcontent.repositories.StationsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/api/")
public class StationApiController {

    private final StationsRepository stationsRepository;
    private final RouteRepository routeRepository;

    public StationApiController(
            StationsRepository stationsRepository,
            RouteRepository routeRepository
    ) {
        this.stationsRepository = stationsRepository;
        this.routeRepository = routeRepository;
    }

    @PostMapping("/stations/create")
    public RedirectView createStation(@ModelAttribute StationForm stationForm) {

        Station station = new Station();
        station.setCity(stationForm.getCity());
        station.setName(stationForm.getName());

        stationsRepository.save(station);

        return new RedirectView("/admin/stations/create");
    }

    @PostMapping("/stations/delete/{id}")
    public RedirectView deleteStation(@PathVariable("id") Long id) {
        List<Route> routesWithDepartureStation = routeRepository.findByDepartureStationId(id);
        List<Route> routesWithArrivalStation = routeRepository.findByArrivalStationId(id);
        List<Route> routesToDelete = new ArrayList<>();
        routesToDelete.addAll(routesWithDepartureStation);
        routesToDelete.addAll(routesWithArrivalStation);
        routeRepository.deleteAll(routesToDelete);

        stationsRepository.deleteById(id);

        return new RedirectView("/admin/stations/create");
    }

    @PostMapping("/stations/update/{id}")
    public RedirectView updateStation(@PathVariable("id") Long id, @ModelAttribute StationForm stationForm) {
        Station station = stationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid train Id:" + id));
        station.setCity(stationForm.getCity());
        station.setName(stationForm.getName());
        stationsRepository.save(station);

        return new RedirectView("/admin/stations/create");
    }
}
