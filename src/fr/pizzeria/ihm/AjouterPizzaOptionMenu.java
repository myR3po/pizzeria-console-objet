package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	
	private Pizza[] pizzas;
	private Scanner scanner;
	private PizzaDaoImpl pizzaDaoImpl;
	

	public AjouterPizzaOptionMenu(PizzaDaoImpl pizzaDaoImpl, Scanner scanner) {
		this.libelle = "2. Ajouter une nouvelle pizza\r";
		
		this.pizzaDaoImpl = pizzaDaoImpl;
		this.setScanner(scanner);
	}
	
	public boolean execute() {
		Pizza pizza = new Pizza();
		
		System.out.println("Veuillez saisir le code");
		pizza.setCode(scanner.nextLine());

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(scanner.nextLine());
		
		System.out.println("Veuillez saisir le prix");
		pizza.setPrice(Double.parseDouble(scanner.nextLine()));
		
		getPizzaDaoImpl().saveNewPizza(pizza);
		
		return true;
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
	
	public PizzaDaoImpl getPizzaDaoImpl() {
		return pizzaDaoImpl;
	}

	public void setPizzaDaoImpl(PizzaDaoImpl pizzaDaoImpl) {
		this.pizzaDaoImpl = pizzaDaoImpl;
	}
	
}
