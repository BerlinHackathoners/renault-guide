package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class Desktop {
	private String page;
	private String edit;
	private String revisions;
	private String talk;

	@Override
	public String toString() {
		return "ClassPojo [page = " + page + ", edit = " + edit + ", revisions = " + revisions + ", talk = " + talk + "]";
	}
}
