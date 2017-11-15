package fr.pizzeria.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;

public class UserInputHelper<T extends StockageException> {

	Scanner scanner;
	T exception;

	public UserInputHelper(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Pizza getUserInput() throws StockageException {
		Pizza pizza = new Pizza();

		try {
			codeInput(pizza);
			nameInput(pizza);
			categoryInput(pizza);
			priceInput(pizza);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		System.out.println(pizza);
		return pizza;
	}

	private void nameInput(Pizza pizza) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, StockageException {

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(scanner.nextLine().trim());

		if (pizza.getName().contains(" ")) {
			throw exception.getClass().getConstructor(String.class).newInstance("The name must not contain space");
		}
	}

	private void categoryInput(Pizza pizza)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, StockageException {
		System.out.println("Veuillez choisir catégorie\n" + "1. Viande\n" + "2. Poisson\n" + "3. Sans viande\n");
		int choix = 0;
		try {
			choix = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException ex) {
			throw exception.getClass().getConstructor(String.class).newInstance("You must enter a integer(eg. 1 or 2)");
		}

		switch (choix) {
		case 1:
			pizza.setCategory(CategoryPizza.VIANDE);
			break;
		case 2:
			pizza.setCategory(CategoryPizza.POISSON);
			break;
		case 3:
			pizza.setCategory(CategoryPizza.SANS_VIANDE);
			break;
		default:
			// throw new savepizzaexception("Invalid choice");
		}
	}

	private void codeInput(Pizza pizza) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, StockageException {
		System.out.println("Veuillez saisir le code");
		pizza.setCode(scanner.nextLine().trim().toUpperCase());

		if (pizza.getCode().length() != 3) {
			throw exception.getClass().getConstructor(String.class).newInstance("code must have 3 characters");
		}
	}

	private void priceInput(Pizza pizza)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, StockageException {
		System.out.println("Veuillez saisir le prix");

		try {
			pizza.setPrice(Double.parseDouble(scanner.nextLine()));
		} catch (NumberFormatException ex) {
			throw exception.getClass().getConstructor(String.class).newInstance("The price must be a number");
		}

		if (pizza.getPrice() <= 0.0) {

			throw exception.getClass().getConstructor(String.class).newInstance("The price cannot be null or negative");
		}
	}
}
