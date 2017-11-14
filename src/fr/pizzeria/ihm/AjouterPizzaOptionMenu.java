package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	
	private Pizza[] pizzas;
	private Scanner scanner;
	private IPizzaDao pizzaDao;
	

	public AjouterPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "2. Ajouter une nouvelle pizza\r";
		
		this.pizzaDao = pizzaDao;
		this.setScanner(scanner);
	}
	
	public boolean execute() throws SavePizzaException {
		Pizza pizza = new Pizza();
		
		System.out.println("Veuillez saisir le code");
		pizza.setCode(scanner.nextLine());
		
		if(pizza.getCode().length() != 3) {
			throw new SavePizzaException("Length must equal 3");
		}

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(scanner.nextLine());
		
		if(pizza.getName().contains(" ")) {
			throw new SavePizzaException("The name must not contain space.");
		}
		
		System.out.println("Veuillez saisir le prix");

		try {
			pizza.setPrice(Double.parseDouble(scanner.nextLine()));
		} catch (NumberFormatException ex) {
			throw new SavePizzaException(ex.getMessage());
		}
		
		boolean done = getPizzaDaoImpl().saveNewPizza(pizza);
		
		if(!done) {
			throw new SavePizzaException("Can't save a pizza");
		}
		
		return done;
	}
	
	public Pizza[] getPizzas() {
		return pizzas;
	}

	public void setPizzas(Pizza[] pizzas) {
		this.pizzas = pizzas;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public IPizzaDao getPizzaDaoImpl() {
		return pizzaDao;
	}

	public void setPizzaDaoImpl(IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
	
}
