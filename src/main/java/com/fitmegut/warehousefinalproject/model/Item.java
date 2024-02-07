package com.fitmegut.warehousefinalproject.model;

public class Item {

	private long itemId;
	private String itemName;
	private ClothingCategory clothingCategories; // dropdown list
	private String itemBrand;
	private String size;
	private String color;
	private ItemCondition itemCondition; // New/...
	private String description;
	private boolean posted;

	public Item(long itemId, ClothingCategory clothingCategories, String itemBrand, String size, String color,
			ItemCondition itemCondition, String description, boolean posted) {
		this.itemId = itemId;
		this.clothingCategories = clothingCategories;
		this.itemBrand = itemBrand;
		this.size = size;
		this.color = color;
		this.itemCondition = itemCondition;
		this.description = description;
		this.posted = posted;
	}

	public Item(long itemId, String itemName, ClothingCategory clothingCategories, String itemBrand, String size,
			String color, ItemCondition itemCondition, String description, boolean posted) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.clothingCategories = clothingCategories;
		this.itemBrand = itemBrand;
		this.size = size;
		this.color = color;
		this.itemCondition = itemCondition;
		this.description = description;
		this.posted = posted;
	}

	public Item() {
	}

	public long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public ItemCondition getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(ItemCondition status) {
		this.itemCondition = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPosted() {
		return posted;
	}

	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	@Override
	public String toString() {
		return "Item Id: " + itemId + ", " + clothingCategories + ", " + itemBrand + ", size " + size + ", " + color
				+ ", " + itemCondition + ", description: " + description + "]";
	}

}
