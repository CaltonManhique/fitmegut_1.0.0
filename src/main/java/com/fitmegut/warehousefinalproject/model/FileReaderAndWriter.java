package com.fitmegut.warehousefinalproject.model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class FileReaderAndWriter {

	private static String pathName = "src/main/resources/static/";

	public void fileWriterUser(User user, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { String.valueOf(user.getId()), user.getFirstName(), user.getLastName(), user.getNickname(),
				user.getDateOfbirth(), user.getGender(), user.getEmail(), user.getPhoneNumber(), user.getCountry(),
				user.getCity(), user.getAddress(), user.getUserType(), user.getPassword() };
		writer.writeNext(data);

		writer.close();
	}

	public Set<User> fileReaderUser(String fileName) throws IOException, CsvValidationException {
		Set<User> arr = new HashSet<User>();

		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {

			User user = new User(Long.parseLong(nextLine[0]), nextLine[1], nextLine[2], nextLine[3], nextLine[4],
					nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10], nextLine[11],
					nextLine[12]);
			arr.add(user);
		}

		reader.close();

		return arr;
	}

	public void fileWriterSession(Session session, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { String.valueOf(session.getSessionID()), String.valueOf(session.getUserID()),
				session.getEmail(), session.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				session.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
				String.valueOf(session.isLoggedIn()) };
		writer.writeNext(data);

		writer.close();
	}

	public Set<Session> fileReaderSession(String fileName) throws IOException, CsvValidationException {
		Set<Session> arr = new HashSet<>();

		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {

			Session session = new Session(Long.parseLong(nextLine[0]), Long.parseLong(nextLine[1]), nextLine[2],
					LocalDate.parse(nextLine[3]), LocalTime.parse(nextLine[4]), Boolean.parseBoolean(nextLine[5]));
			arr.add(session);
		}

		reader.close();

		return arr;
	}

	// Updates the file overwriting all data in the file
	public void updateFile(Set<User> users, String fileName) throws IOException {
		Set<String[]> arr = new HashSet<String[]>();
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file);

		CSVWriter writer = new CSVWriter(outputFile);

		for (User user : users) {
			String[] data = { String.valueOf(user.getId()), user.getFirstName(), user.getLastName(), user.getNickname(),
					user.getDateOfbirth(), user.getGender(), user.getEmail(), user.getPhoneNumber(), user.getCountry(),
					user.getCity(), user.getAddress(), user.getUserType(), user.getPassword() };
			arr.add(data);
		}
		writer.writeAll(arr);

		writer.close();
	}

	public void updateFileSession(Set<Session> sessions, String fileName) throws IOException {
		Set<String[]> arr = new HashSet<String[]>();
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file);

		CSVWriter writer = new CSVWriter(outputFile);

		for (Session session : sessions) {
			String[] data = { String.valueOf(session.getSessionID()), String.valueOf(session.getUserID()),
					session.getEmail(), session.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					session.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
					String.valueOf(session.isLoggedIn()) };
			arr.add(data);
		}
		writer.writeAll(arr);

		writer.close();
	}

	public void fileWriterWardrobe(Wardrobe wardrobe, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { String.valueOf(wardrobe.getWardrobeID()), String.valueOf(wardrobe.getUserID()),
				String.valueOf(wardrobe.getItemID()), wardrobe.getClothingCategory().toString(),
				wardrobe.getItemBrand(), String.valueOf(wardrobe.isPosted()) };
		writer.writeNext(data);

		writer.close();
	}

	public List<Wardrobe> fileReaderWardrobe(String fileName) throws IOException, CsvValidationException {
		List<Wardrobe> arr = new ArrayList<>();

		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {

			Wardrobe wardrobe = new Wardrobe(Long.parseLong(nextLine[0]), Long.parseLong(nextLine[1]),
					Long.parseLong(nextLine[2]), ClothingCategory.valueOf(nextLine[3]), nextLine[4],
					Boolean.parseBoolean(nextLine[5]));
			arr.add(wardrobe);
		}

		reader.close();

		return arr;
	}

	public void fileWriterItem(Item item, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { String.valueOf(item.getItemId()), item.getClothingCategories().toString(),
				item.getItemBrand(), item.getSize(), item.getColor(), item.getStatus(), item.getDescription() };
		writer.writeNext(data);

		writer.close();
	}

	public List<Item> fileReaderItem(String fileName) throws IOException, CsvValidationException {
		List<Item> arr = new ArrayList<>();

		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {

			Item item = new Item(Long.parseLong(nextLine[0]), ClothingCategory.valueOf(nextLine[1]), nextLine[2],
					nextLine[3], nextLine[4], nextLine[5], nextLine[6]);
			arr.add(item);
		}

		reader.close();

		return arr;
	}

	public void updateFileWardrobe(List<Wardrobe> wardrobes, String fileName) throws IOException {
		List<String[]> arr = new ArrayList<String[]>();
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file);

		CSVWriter writer = new CSVWriter(outputFile);

		for (Wardrobe wardrobe : wardrobes) {
			String[] data = { String.valueOf(wardrobe.getWardrobeID()), String.valueOf(wardrobe.getUserID()),
					String.valueOf(wardrobe.getItemID()), wardrobe.getClothingCategory().toString(),
					wardrobe.getItemBrand(), String.valueOf(wardrobe.isPosted()) };
			arr.add(data);
		}
		writer.writeAll(arr);

		writer.close();
	}
}