package com.fitmegut.warehousefinalproject.model;

public enum ItemCondition {

	BRAND_NEW("NEW WITHOUT TAGS"), NEW("NEW WITH TAGS"), EXCELLENT("EXCELLENT USED CONDITION"),
	GOOD("GOOD USED CONDITION"), USED("VERY USED CONDITION");

	private String meaning;

	private ItemCondition(String meaning) {
		this.meaning = meaning;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

}
