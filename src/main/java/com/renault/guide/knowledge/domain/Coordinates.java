package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class Coordinates {
	private String lon;
	private String lat;

	@Override
	public String toString() {
		return "ClassPojo [lon = " + lon + ", lat = " + lat + "]";
	}
}

