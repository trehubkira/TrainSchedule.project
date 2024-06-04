package com.example.servingwebcontent.repositories;

import com.example.servingwebcontent.models.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationsRepository extends CrudRepository<Station, Long> {
}
