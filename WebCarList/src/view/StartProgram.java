package view;

import java.util.List;
import java.util.Scanner;

import controller.CarItemHelper;
import model.CarItem;

public class StartProgram {

	// scanner
	static Scanner in = new Scanner(System.in);
	static CarItemHelper cih = new CarItemHelper();

	// adding an item
	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter make: ");
		String make = in.nextLine();
		System.out.print("Enter a year: ");
		String year = in.nextLine();
		System.out.print("Enter a model: ");
		String model = in.nextLine();
		CarItem toAdd = new CarItem(make, year, model);
		cih.insertItem(toAdd);
	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter in make to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the year to delete: ");
		String year = in.nextLine();
		System.out.print("Enter the model to delete: ");
		String model = in.nextLine();

		CarItem toDelete = new CarItem(make, year, model);
		cih.deleteItem(toDelete);

	}

	// editing an item
	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Make");
		System.out.println("2 : Search by Year");
		System.out.println("3 : Search by Model");
		int searchBy = in.nextInt();
		in.nextLine();

		List<CarItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the make: ");
			String make = in.nextLine();
			foundItems = cih.searchForItemByMake(make);
		} else if (searchBy == 2) {
			System.out.print("Enter the year: ");
			String year = in.nextLine();
			foundItems = cih.searchForItemByYear(year);
		} else {
			System.out.print("Enter the model: ");
			String modelName = in.nextLine();
			foundItems = cih.searchForItemByModel(modelName);
		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (CarItem c : foundItems) {
				System.out.println(c.getId() + " : " + c.toString());
			}
			System.out.print("Which make to edit: ");
			int idToEdit = in.nextInt();

			CarItem toEdit = cih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getId() + " from " + toEdit.getMake());
			System.out.println("1: Update Make");
			System.out.println("2 : Update Year");
			System.out.println("3 : Update Model");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New make: ");
				String newMake = in.nextLine();
				toEdit.setMake(newMake);
			} else if (update == 2){
				System.out.print("New year: ");
				String newYear = in.nextLine();
				toEdit.setYear(newYear);
			} else if (update == 3) {
				System.out.print("New Model: ");
				String newModel = in.nextLine();
				toEdit.setModel(newModel);
			}

			cih.updateModel(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to the list of cars!! ---");
		while (goAgain) {
			System.out.println("*  Select a car:");
			System.out.println("*  1 -- Add a car");
			System.out.println("*  2 -- Edit a car");
			System.out.println("*  3 -- Delete a car");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				cih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<CarItem> allItems = cih.showAllItems();
		for (CarItem singleItem : allItems) {
			System.out.println(singleItem.returnItemDetails());
		}

	}

}
