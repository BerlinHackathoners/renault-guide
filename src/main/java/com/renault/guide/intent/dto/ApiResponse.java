package com.visualmeta.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.visualmeta.utils.JsonDto;

public class ApiResponse implements JsonDto
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{ return true; }
		if (o == null || getClass() != o.getClass())
		{ return false; }

		ApiResponse that = (ApiResponse) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
		{ return false; }
		if (lang != null ? !lang.equals(that.lang) : that.lang != null)
		{ return false; }
		return result != null ? result.equals(that.result) : that.result == null;
	}

	@Override
	public int hashCode()
	{
		int result1 = id != null ? id.hashCode() : 0;
		result1 = 31 * result1 + (lang != null ? lang.hashCode() : 0);
		result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
		return result1;
	}
}
