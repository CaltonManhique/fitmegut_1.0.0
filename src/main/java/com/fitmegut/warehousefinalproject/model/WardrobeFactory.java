package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fitmegut.warehousefinalproject.exception.Validations;
import com.opencsv.exceptions.CsvValidationException;

public class WardrobeFactory {

	private final String wardrobeFileName = "wardrobe.csv";
	private final String itemFileName = "items.csv";
	private final String clothingCategoriesFile = "src/main/resources/static/categories.txt";

	private List<Wardrobe> wardrobe;
	private List<Item> items;

	private Scanner scanner = new Scanner(System.in);
	Validations validations = new Validations();
	FileReaderAndWriter fileReaderAndWriter = new FileReaderAndWriter();

	private long wardrobeID = 10;
	private long itemID = 10;

	public WardrobeFactory() {
		wardrobe = new ArrayList<Wardrobe>();
		items = new ArrayList<Item>();
	}

	public long wardrobeIdGenerator() {
		return wardrobe.isEmpty() ? wardrobeID : wardrobeID + 1;
	}

	public Long getLastWardrobeID() {
		return wardrobe.stream().mapToLong(Wardrobe::getWardrobeID).max().getAsLong();
	}

	public long itemIdGenerator() {
		return items.isEmpty() ? itemID : itemID + 1;
	}

	public Long getLastItemID() {
		return items.stream().mapToLong(Item::getItemId).max().getAsLong();
	}

	private Item createItem(ClothingCategory clothingCategories, String itemBrand, String size, String color,
			String status, String description) {
		return new Item(itemIdGenerator(), clothingCategories, itemBrand, size, color, status, description);
	}

	private Wardrobe createWardrobeEntry(long userID, long itemID, ClothingCategory clothingCategory,
			String itemBrand) {
		return new Wardrobe(wardrobeIdGenerator(), userID, itemID, clothingCategory, itemBrand, false);
	}

	public void addItemToTheWardrobe(long userId) throws IOException {

		try {

			wardrobe = fileReaderAndWriter.fileReaderWardrobe(wardrobeFileName);
			wardrobeID = getLastWardrobeID();

			items = fileReaderAndWriter.fileReaderItem(itemFileName);
			itemID = getLastItemID();

		} catch (CsvValidationException | IOException e) {
		}

		System.out.println("Select a category bellow:");
		Files.lines(Paths.get(clothingCategoriesFile)).forEach(System.out::println); // Dropdown list

		ClothingCategory category = switch (scanner.nextInt()) {
		case 1 -> ClothingCategory.SWEATER;
		case 2 -> ClothingCategory.DRESS;
		case 3 -> ClothingCategory.HOODIES;
		case 4 -> ClothingCategory.T_SHIRT;
		case 5 -> ClothingCategory.SHORTS;
		case 6 -> ClothingCategory.SKIRT;
		case 7 -> ClothingCategory.JEANS;
		case 8 -> ClothingCategory.SHOES;
		case 9 -> ClothingCategory.COAT;
		case 10 -> ClothingCategory.SUIT;
		case 11 -> ClothingCategory.SHIRT;
		case 12 -> ClothingCategory.JACKET;
		case 13 -> ClothingCategory.OTHER;
		default -> throw new IllegalArgumentException("Unexpected value!! ");
		};

		scanner.nextLine();

		System.out.println("Enter brand:");
		String itemBrand = scanner.nextLine(); // Mandatory field

		System.out.println("Enter size:");
		String size = scanner.nextLine(); // Mandatory field

		System.out.println("Enter color:");
		String color = scanner.nextLine(); // Mandatory field

		System.out.println("Enter status (New/Used):");
		String status = scanner.nextLine(); // Mandatory field

		System.out.println("Enter description:");
		String description = scanner.nextLine();

		Item addedItem = createItem(category, itemBrand, size, color, status, description);
		items.add(addedItem);

		Wardrobe wardrobeEntry = createWardrobeEntry(userId, addedItem.getItemId(), addedItem.getClothingCategories(),
				addedItem.getItemBrand());

		wardrobe.add(wardrobeEntry);

		fileReaderAndWriter.fileWriterItem(addedItem, itemFileName);
		fileReaderAndWriter.fileWriterWardrobe(wardrobeEntry, wardrobeFileName);

		System.out.println("Item added successfully to the wardrobe.");
	}

	public void removeItemFromWardrobe(long wardrobeID, long itemID) {
		items.removeIf(iId -> iId.getItemId() == itemID);
		wardrobe.removeIf(wId -> wId.getWardrobeID() == wardrobeID);
		System.out.println("Item removed from wardrobe!!");
	}

	public void postItemForExchange(long itemID) {
		Item item = items.stream().filter(i -> i.getItemId() == itemID).findAny().get();

		wardrobe.forEach(w -> {
			if (w.getItemID() == itemID)
				w.setPosted(true);
		});

		try {
			fileReaderAndWriter.updateFileWardrobe(wardrobe, wardrobeFileName);
			System.out.println("Item posted: \n" + item.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
