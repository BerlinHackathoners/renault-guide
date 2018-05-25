package com.visualmeta.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameters
{
	@JsonProperty("brand")
	public String brand;
/*	@JsonProperty("gender")
	public String gender;*/
	@JsonProperty("category")
	public String category;
	@JsonProperty("color")
	public String color;
	@JsonProperty("material")
	public String material;
	@JsonProperty("max_price")
	public String maxPrice;
	/*@JsonProperty("min_price")
	public String minPrice;
	@JsonProperty("promotion")
	public String promotion;*/

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

/*
	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
*/

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getMaterial()
	{
		return material;
	}

	public void setMaterial(String material)
	{
		this.material = material;
	}

	public String getMaxPrice()
	{
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice)
	{
		this.maxPrice = maxPrice;
	}

	/*public String getMinPrice()
	{
		return minPrice;
	}

	public void setMinPrice(String minPrice)
	{
		this.minPrice = minPrice;
	}

	public String getPromotion()
	{
		return promotion;
	}

	public void setPromotion(String promotion)
	{
		this.promotion = promotion;
	}*/

	@Override
	public String toString()
	{
		return "Parameters{" + "brand='" + brand + '\'' + ", category='" + category + '\'' + ", color='" + color + '\'' + ", material='" + material
			+ '\'' + ", maxPrice='" + maxPrice + '\'' + '}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{ return true; }
		if (o == null || getClass() != o.getClass())
		{ return false; }

		Parameters that = (Parameters) o;

		if (brand != null ? !brand.equals(that.brand) : that.brand != null)
		{ return false; }
		if (category != null ? !category.equals(that.category) : that.category != null)
		{ return false; }
		if (color != null ? !color.equals(that.color) : that.color != null)
		{ return false; }
		if (material != null ? !material.equals(that.material) : that.material != null)
		{ return false; }
		return maxPrice != null ? maxPrice.equals(that.maxPrice) : that.maxPrice == null;
	}

	@Override
	public int hashCode()
	{
		int result = brand != null ? brand.hashCode() : 0;
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + (color != null ? color.hashCode() : 0);
		result = 31 * result + (material != null ? material.hashCode() : 0);
		result = 31 * result + (maxPrice != null ? maxPrice.hashCode() : 0);
		return result;
	}
}
