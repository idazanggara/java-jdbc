package com.enigma.jdbc.dto;

public class MenuDetailResponse {
	private Integer menuId;
	private Integer menuPriceId;
	private String name;
	private Float price;

	public MenuDetailResponse(Integer menuId, Integer menuPriceId, String name, Float price) {
		this.menuId = menuId;
		this.menuPriceId = menuPriceId;
		this.name = name;
		this.price = price;
	}

	public MenuDetailResponse() {
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuPriceId() {
		return menuPriceId;
	}

	public void setMenuPriceId(Integer menuPriceId) {
		this.menuPriceId = menuPriceId;
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
