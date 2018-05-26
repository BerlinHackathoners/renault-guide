package com.renault.guide.intent.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse
{

	@JsonProperty("id")
	private String id;
	@JsonProperty("lang")
	private String lang;
	@JsonProperty("result")
	private Result result;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getLang()
	{
		return lang;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
	}

	public Result getResult()
	{
		return result;
	}

	public void setResult(Result result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "ApiResponse{" + "id='" + id + '\'' + ", lang='" + lang + '\'' + ", result=" + result + '}';
	}

}
