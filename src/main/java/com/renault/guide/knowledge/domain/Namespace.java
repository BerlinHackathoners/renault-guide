package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class Namespace {
	private String id;
	private String text;

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", text = " + text + "]";
	}
}
