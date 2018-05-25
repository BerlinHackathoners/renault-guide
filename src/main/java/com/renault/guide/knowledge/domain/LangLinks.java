package com.renault.guide.knowledge.domain;

public class LangLinks {
	private String name;
	private String lang;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "LangLinks{" +
		       "name='" + name + '\'' +
		       ", lang='" + lang + '\'' +
		       '}';
	}
}
