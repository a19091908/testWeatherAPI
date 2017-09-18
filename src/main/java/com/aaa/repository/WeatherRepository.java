package com.aaa.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aaa.model.Weather;


@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
	List<Weather> findByLocationNameContaining(String locationName);

}
