package com.renault.guide.knowledge.domain;

public class WikiResult {
	private Query query;
	private String batchcomplete;

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getBatchcomplete() {
		return batchcomplete;
	}

	public void setBatchcomplete(String batchcomplete) {
		this.batchcomplete = batchcomplete;
	}

	@Override
	public String toString() {
		return "WikiResult{" +
		       "query=" + query +
		       ", batchcomplete='" + batchcomplete + '\'' +
		       '}';
	}
}
