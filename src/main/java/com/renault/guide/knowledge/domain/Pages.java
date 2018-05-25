package com.renault.guide.knowledge.domain;

public class Pages {
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Pages{" +
		       "result=" + result +
		       '}';
	}
}
