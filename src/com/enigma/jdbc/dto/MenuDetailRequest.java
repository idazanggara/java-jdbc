package com.enigma.jdbc.dto;

public class MenuDetailRequest {
	private String name;
	private Float price;

	public MenuDetailRequest(String name, Float price) {
		this.name = name;
		this.price = price;
	}

	public MenuDetailRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}
