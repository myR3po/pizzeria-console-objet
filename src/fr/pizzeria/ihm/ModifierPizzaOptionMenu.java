package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;

public class ModifierPizzaOptionMenu extends OptionMenu {

	private Scanner scanner;
	private IPizzaDao pizzaDao;

	public ModifierPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "3. Mettre à jour une pizza\r";
		this.setScanner(scanner);
		this.pizzaDao = pizzaDao;
	}

	public boolean execute() throws UpdatePizzaException {
		Pizza pizza = null;
		String code = null;
		boolean done;
		
		for(Pizza p : this.getPizzaDao().findAllPizzas()) {
			if(p != null) {
				System.out.println(p);
			}
		}
		System.out.println();

		System.out.println("Veuillez choisir le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			pizza = new Pizza();

			System.out.println("Veuillez saisir le code");
			pizza.setCode(scanner.nextLine().trim());
			
			if(pizza.getCode().length() != 3) {
				throw new UpdatePizzaException("code must have 3 characters");
			}

			System.out.println("Veuillez saisir le nom (sans espace)");
			pizza.setName(scanner.nextLine().trim());
			
			if(pizza.getName().contains(" ")) {
				throw new UpdatePizzaException("The name must not contain space.");
			}
			
			System.out.println("Veuillez choisir catégorie\n"
					+ "1. Viande\n"
					+ "2. Poisson\n"
					+ "3. Sans viande\n");
			int choix = 0;
			try {
				choix = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException ex) {
				throw new UpdatePizzaException("You must enter a integer(eg. 1 or 2)");
			}
			
			switch(choix) {
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
					throw new UpdatePizzaException("Invalid choice");
			}

			System.out.println("Veuillez saisir le prix");
			
			try {
				pizza.setPrice(Double.parseDouble(scanner.nextLine()));
			} catch (NumberFormatException ex) {
				throw new UpdatePizzaException("The price must be a number");
			}
			
			if(pizza.getPrice() <= 0.0) {
				throw new UpdatePizzaException("The price cannot be null or negative");
			}
			
			done = this.getPizzaDao().updatePizza(code, pizza);

			if (!done) {
				throw new UpdatePizzaException("Nous n'avons pas cette pizza.");
			}

		}

		return true;

	}
	
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public IPizzaDao getPizzaDao() {
		return pizzaDao;
	}

	public void setPizzaDao(IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
