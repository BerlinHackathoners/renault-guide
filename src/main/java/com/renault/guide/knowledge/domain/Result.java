package com.renault.guide.knowledge.domain;

import lombok.Data;

import java.util.Arrays;

@Data
public class Result {
	private String title;
	private String ns;
	private LangLinks[] langlinks;
	private String pageid;
	private String extract;

	@Override
	public String toString() {
		return "Result{" +
				"title='" + title + '\'' +
				", ns='" + ns + '\'' +
				", langlinks=" + Arrays.toString(langlinks) +
				", pageid='" + pageid + '\'' +
				'}';
	}
}
