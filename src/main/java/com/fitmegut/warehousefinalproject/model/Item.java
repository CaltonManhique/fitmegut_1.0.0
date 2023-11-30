package com.fitmegut.warehousefinalproject.model;

public class Item {

	private long itemId;
	private ClothingCategory clothingCategories; // dropdown list
	private String itemBrand;
	private String size;
	private String color;
	private String status; // New/...
	private String description;

	public Item(long itemId, ClothingCategory clothingCategories, String itemBrand, String size, String color,
			String status, String description) {
		this.itemId = itemId;
		this.clothingCategories = clothingCategories;
		this.itemBrand = itemBrand;
		this.size = size;
		this.color = color;
		this.status = status;
		this.description = description;
	}


	public Item() {
	}


	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemBrand() {
		return itemBrand;
	}


	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}


	public ClothingCategory getClothingCategories() {
		return clothingCategories;
	}

	public void setClothingCategories(ClothingCategory clothingCategories) {
		this.clothingCategories = clothingCategories;
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


	@Override
	public String toString() {
		return "Item Id: " + itemId + ", " + clothingCategories + ", " + itemBrand
				+ ", size " + size + ", " + color + ", " + status + ", description: " + description + "]";
	}

	
}
