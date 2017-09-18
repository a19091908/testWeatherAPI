package com.aaa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WeatherElement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// @Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	private String elementName;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<Time> time;

	public WeatherElement() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public List<Time> getTime() {
		return time;
	}

	public void setTime(List<Time> time) {
		this.time = time;
	}

}
