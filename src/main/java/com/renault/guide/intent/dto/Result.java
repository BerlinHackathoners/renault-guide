package com.visualmeta.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result
{
	@JsonProperty("parameters")
	private Parameters parameters;
	@JsonProperty("metadata")
	private MetaData metaData;
	@JsonProperty("score")
	private double score;

	public Parameters getParameters()
	{
		return parameters;
	}

	public void setParameters(Parameters parameters)
	{
		this.parameters = parameters;
	}

	public MetaData getMetaData()
	{
		return metaData;
	}

	public void setMetaData(MetaData metaData)
	{
		this.metaData = metaData;
	}

	public double getScore()
	{
		return score;
	}

	public void setScore(double score)
	{
		this.score = score;
	}

	@Override
	public String toString()
	{
		return "Result{" + "parameters=" + parameters + ", metaData=" + metaData + ", score='" + score + '\'' + '}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{ return true; }
		if (o == null || getClass() != o.getClass())
		{ return false; }

		Result result = (Result) o;

		if (Double.compare(result.score, score) != 0)
		{ return false; }
		if (parameters != null ? !parameters.equals(result.parameters) : result.parameters != null)
		{ return false; }
		return metaData != null ? metaData.equals(result.metaData) : result.metaData == null;
	}

	@Override
	public int hashCode()
	{
		int result;
		long temp;
		result = parameters != null ? parameters.hashCode() : 0;
		result = 31 * result + (metaData != null ? metaData.hashCode() : 0);
		temp = Double.doubleToLongBits(score);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
