package com.renault.guide.intent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameters
{
	@JsonProperty("places")
	public String places;


	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}
}
