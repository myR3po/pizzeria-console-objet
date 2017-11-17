package fr.pizzeria.util;

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

	public Pizza getUserInput() throws T  {
		Pizza pizza = new Pizza();

			codeInput(pizza);
			nameInput(pizza);
			categoryInput(pizza);
			priceInput(pizza);

		System.out.println(pizza);
		return pizza;
	}

	@SuppressWarnings("unchecked")
	private void nameInput(Pizza pizza) throws T {

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(scanner.nextLine().trim());

		if (!pizza.getName().matches("[a-zA-Z]")){
			throw (T) new StockageException("The name contains an invalid character");
		}
	}

	@SuppressWarnings("unchecked")
	private void categoryInput(Pizza pizza) throws T {
		System.out.println("Veuillez choisir catégorie\n" + "1. Viande\n" + "2. Poisson\n" + "3. Sans viande\n");
		int choix = 0;
		try {
			choix = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException ex) {
			throw (T) new StockageException("You must enter a integer(eg. 1 or 2...)");
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
			 throw (T) new StockageException("Invalid choice");
		}
	}

	@SuppressWarnings("unchecked")
	private void codeInput(Pizza pizza) throws T {
		System.out.println("Veuillez saisir le code");
		pizza.setCode(scanner.nextLine().trim().toUpperCase());
		
		if(!pizza.getCode().matches("[a-zA-Z]")) {
			throw (T) new StockageException("code contains an invalid character");
		}
		
		if (pizza.getCode().length() != 3) {
			throw (T) new StockageException("code must have 3 characters");
		}
	}

	@SuppressWarnings("unchecked")
	private void priceInput(Pizza pizza) throws T {
		System.out.println("Veuillez saisir le prix");

		try {
			pizza.setPrice(Double.parseDouble(scanner.nextLine()));
		} catch (NumberFormatException ex) {
			throw (T) new StockageException("The price must be a number");
		}

		if (pizza.getPrice() <= 0.0) {

			throw (T) new StockageException("The price cannot be null or negative");
		}
	}
}
