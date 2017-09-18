package com.aaa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Weather {
	@Id
	@GenericGenerator(strategy = "increment", name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// @Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	private String locationName;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<WeatherElement> weatherElement;

	public Weather() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public List<WeatherElement> getWeatherElement() {
		return weatherElement;
	}

	public void setWeatherElement(List<WeatherElement> weatherElement) {
		this.weatherElement = weatherElement;
	}

}
