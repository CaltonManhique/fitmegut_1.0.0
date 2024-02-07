package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fitmegut.warehousefinalproject.exception.Validations;
import com.fitmegut.warehousefinalproject.service.DbConnections;
import com.opencsv.exceptions.CsvValidationException;

public class WardrobeFactory {

	private final String wardrobeFileName = "wardrobe.csv";
	private final String itemFileName = "items.csv";
	private final static String TXT_PATH = "src/main/resources/static/";

	private List<Wardrobe> wardrobe;
	private List<Item> items;

	private Scanner scanner = new Scanner(System.in);
	private Validations validations = new Validations();
	private FileReaderAndWriter fileReaderAndWriter = new FileReaderAndWriter();

	private DbConnections dbConnections;

	private long wardrobeID = 10;
	private long itemID = 10;

	public WardrobeFactory() {
//		wardrobe = new ArrayList<Wardrobe>();
//		items = new ArrayList<Item>();
		dbConnections = new DbConnections();
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

	// csv version
//	private Item createItem(ClothingCategory clothingCategories, String itemBrand, String size, String color,
//			String itemCondition, String description) {
//		return new Item(itemIdGenerator(), clothingCategories, itemBrand, size, color, itemCondition, description);
//	}
//
//	private Item createItem(String itemName, ClothingCategory clothingCategories, String itemBrand, String size,
//			String color, String itemCondition, String description) {
//		return new Item(itemIdGenerator(), itemName, clothingCategories, itemBrand, size, color, itemCondition,
//				description);
//	}

	private Item createItem() throws IOException, SQLException {

		System.out.println("Enter item name:");
		String itemName = scanner.nextLine();

		System.out.println("Select a category bellow:");
		Files.lines(Paths.get(TXT_PATH + "categories.txt")).forEach(System.out::println); // Dropdown list

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

		System.out.println("Select the item condition from the bellow list:");
		Files.lines(Paths.get(TXT_PATH + "item_condition.txt")).forEach(System.out::println);
		ItemCondition itemCondition = switch (scanner.nextInt()) {
		case 1 -> ItemCondition.BRAND_NEW;
		case 2 -> ItemCondition.NEW;
		case 3 -> ItemCondition.EXCELLENT;
		case 4 -> ItemCondition.GOOD;
		case 5 -> ItemCondition.USED;
		default -> throw new IllegalArgumentException("Unexpected value!!");
		};

		scanner.nextLine();

		System.out.println("Enter Notes:");
		String description = scanner.nextLine();

		return dbConnections.insertItem(itemName, category, itemBrand, size, color, itemCondition, description);
	}

	public void addItemToTheWardrobe(long userId) throws IOException {

		try {
			Item item = createItem();

			if (item == null) {
				System.out.println("Item not found/created to be added to the wardrobe!!");
			} else {
				dbConnections.createWardrobeEntry(userId, item.getItemId(), item.getClothingCategories(),
						item.getItemBrand(), item.isPosted());
				System.out.println("Item added successfully to the wardrobe.");
			}

		} catch (IOException | SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
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
