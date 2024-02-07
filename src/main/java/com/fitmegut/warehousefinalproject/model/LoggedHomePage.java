package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LoggedHomePage {

	private final String homepageAfterLogin = "src/main/resources/static/homepage_after_login.txt";

	private WardrobeFactory wardrobeFactory;
	private LogOff logOff;

	private Scanner scanner = new Scanner(System.in);

	public LoggedHomePage() {
		wardrobeFactory = new WardrobeFactory();
		logOff = new LogOff();
	}

	public void loggedMember(long sessionID, long userID) throws IOException {

		Files.lines(Paths.get(homepageAfterLogin)).forEach(System.out::println);

		int option = 0;

		do {

			option = scanner.nextInt();

			switch (option) {

			case 1 -> {

				System.out.println("5. Settings\n6. Log out");
				option = scanner.nextInt();

				switch (option) {
				case 5 -> System.out.println("Settings - to be implemented");
				case 6 -> logOff.logOff(sessionID);

				default -> throw new IllegalArgumentException("Unexpected value: " + option);
				}
			}

			case 2 -> {

				System.out.println("7. Add item\n8. Post item for exchange\n9. Donate item\n10. Remove item");
				option = scanner.nextInt();

				switch (option) {
				case 7 -> {
					wardrobeFactory.addItemToTheWardrobe(userID);
					Files.lines(Paths.get(homepageAfterLogin)).forEach(System.out::println);
				}
				case 8 -> {
					System.out.println("Enter item id:");
					long itemId = scanner.nextLong();
					wardrobeFactory.postItemForExchange(itemId);
				}

				case 9 -> System.out.println("Donate item - to be implemented.");

				case 10 -> {

					System.out.println("Enter item id:");
					long itemId = scanner.nextLong();

					System.out.println("Enter wardrobe id:");
					long wradrobeId = scanner.nextLong();

					wardrobeFactory.removeItemFromWardrobe(wradrobeId, itemId);
				}

				default -> throw new IllegalArgumentException("Unexpected value: " + option);
				}
			}

			case 3 -> System.out.println("Donation - to be implemented");
			case 4 -> System.out.println("Exchange - to be implemented");

			default -> throw new IllegalArgumentException("Unexpected value: " + option);
			}

		} while (option != 6);

		new HomePage().runApp();
	}

}
