package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class Thumbnail {
	private String height;
	private String source;
	private String width;

	@Override
	public String toString() {
		return "ClassPojo [height = " + height + ", source = " + source + ", width = " + width + "]";
	}
}