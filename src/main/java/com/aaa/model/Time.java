package com.aaa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// @Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	private String startTime;

	private String endTime;

	@OneToOne(cascade = { CascadeType.ALL })
	private Parameter parameter;

	public Time() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
