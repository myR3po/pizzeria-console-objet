package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {

	private Scanner scanner;
	private IPizzaDao pizzaDao;
	

	public AjouterPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "2. Ajouter une nouvelle pizza\r";
		this.pizzaDao = pizzaDao;
		this.setScanner(scanner);
	}
	
	public boolean execute() throws SavePizzaException {
		Pizza pizza = new Pizza();
		
		for(Pizza p : this.getPizzaDao().findAllPizzas()) {
			if(p != null) {
				System.out.println(p);
			}
		}
		System.out.println();
		
		System.out.println("Veuillez saisir le code");
		pizza.setCode(scanner.nextLine().trim());
		
		if(pizza.getCode().length() != 3) {
			throw new SavePizzaException("code must have 3 characters");
		}

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(scanner.nextLine().trim());
		
		if(pizza.getName().contains(" ")) {
			throw new SavePizzaException("The name must not contain space");
		}
				
		System.out.println("Veuillez choisir catégorie\n"
				+ "1. Viande\n"
				+ "2. Poisson\n"
				+ "3. Sans viande\n");
		int choix = 0;
		try {
			choix = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException ex) {
			throw new SavePizzaException("You must enter a integer(eg. 1 or 2)");
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
				throw new SavePizzaException("Invalid choice");
		}
		
		System.out.println("Veuillez saisir le prix");

		try {
			pizza.setPrice(Double.parseDouble(scanner.nextLine()));
		} catch (NumberFormatException ex) {
			throw new SavePizzaException("The price must be a number");
		}
		
		if(pizza.getPrice() < 0.0) {
			throw new SavePizzaException("The price cannot be null or negative");
		}
		
		boolean done = getPizzaDao().saveNewPizza(pizza);
		
		if(!done) {
			throw new SavePizzaException("Can't save a pizza");
		}
		
		return done;
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
