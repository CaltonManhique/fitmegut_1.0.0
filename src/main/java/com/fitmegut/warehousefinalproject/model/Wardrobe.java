package com.fitmegut.warehousefinalproject.model;

public class Wardrobe {

	private Long id;
	private String item;
	private String size;
	private String color;
	private String status; // New/...
	private String description;

	public Wardrobe(String item, String size, String color, String status, String description) {
		this.item = item;
		this.size = size;
		this.color = color;
		this.status = status;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
