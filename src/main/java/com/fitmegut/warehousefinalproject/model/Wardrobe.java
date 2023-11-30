package com.fitmegut.warehousefinalproject.model;

import java.util.List;

public class Wardrobe {

	private long wardrobeID; // To generated using spring
	private long userID;
	private long itemID;
	private ClothingCategory clothingCategory;
	private String itemBrand;
	private boolean posted;
	private List<Item> clothing; // ??

	public Wardrobe(long wardrobeID, long userID, long itemID, ClothingCategory clothingCategory, String itemBrand,
			boolean posted) {
		this.wardrobeID = wardrobeID;
		this.userID = userID;
		this.itemID = itemID;
		this.clothingCategory = clothingCategory;
		this.itemBrand = itemBrand;
	}

	public long getWardrobeID() {
		return wardrobeID;
	}

	public long getUserID() {
		return userID;
	}

	public long getItemID() {
		return itemID;
	}

	public ClothingCategory getClothingCategory() {
		return clothingCategory;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public boolean isPosted() {
		return posted;
	}

	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	public List<Item> getClothing() {
		return clothing;
	}

}
