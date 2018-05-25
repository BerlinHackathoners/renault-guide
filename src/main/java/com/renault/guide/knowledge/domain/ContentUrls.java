package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class ContentUrls {
	private Desktop desktop;
	private Mobile mobile;

	@Override
	public String toString() {
		return "ClassPojo [desktop = " + desktop + ", mobile = " + mobile + "]";
	}
}
