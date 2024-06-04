package com.example.servingwebcontent.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RouteForm {

    private Long departureStationId;
    private Long arrivalStationId;
    private Long trainId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date end;

    public Long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Long departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Long getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Long arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
