package com.renault.guide.knowledge.domain;

public class Query {
	private Pages pages;

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Query{" +
		       "pages=" + pages +
		       '}';
	}
}
