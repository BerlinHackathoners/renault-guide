package com.renault.guide.intent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData
{
	@JsonProperty("intentName")
	public String intentName;

	public String getIntentName()
	{
		return intentName;
	}

	public void setIntentName(String intentName)
	{
		this.intentName = intentName;
	}

	@Override
	public String toString()
	{
		return "MetaData{" + "intentName='" + intentName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{ return true; }
		if (o == null || getClass() != o.getClass())
		{ return false; }

		MetaData metaData = (MetaData) o;

		return intentName != null ? intentName.equals(metaData.intentName) : metaData.intentName == null;
	}

	@Override
	public int hashCode()
	{
		return intentName != null ? intentName.hashCode() : 0;
	}
}
