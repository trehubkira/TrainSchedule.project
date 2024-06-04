package com.example.servingwebcontent.repositories;

import com.example.servingwebcontent.models.Route;
import com.example.servingwebcontent.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.departureStation = ?1 AND r.arrivalStation = ?2 AND DATE(r.start) = DATE(?3)")
    List<Route> findAllByDepartureStationAndArrivalStationAndStart(Station departureStation, Station arrivalStation, Date start);
    List<Route> findByDepartureStationId(Long departureStationId);
    List<Route> findByArrivalStationId(Long arrivalStationId);
}
