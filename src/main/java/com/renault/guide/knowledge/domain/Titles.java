package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class Titles {
	private String normalized;
	private String display;
	private String canonical;

	@Override
	public String toString() {
		return "ClassPojo [normalized = " + normalized + ", display = " + display + ", canonical = " + canonical + "]";
	}
}

