package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	
	private Pizza[] pizzas;
	private Scanner scanner;
	
	public AjouterPizzaOptionMenu(Pizza[] pizzas, Scanner scanner) {
		this.libelle = "2. Ajouter une nouvelle pizza\r";
		this.setPizzas(pizzas);
		this.setScanner(scanner);
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

	public boolean execute() {
		int i = 0;
		Pizza pizza = new Pizza();
		boolean done = false;
		
		System.out.println("Veuillez saisir le code");
		pizza.setCode(scanner.nextLine());

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(scanner.nextLine());
		
		System.out.println("Veuillez saisir le prix");
		pizza.setPrice(Double.parseDouble(scanner.nextLine()));
		
		do {
			
			if(getPizzas()[i] == null) {
				getPizzas()[i] = pizza;
				done = true;
			}
			i++;
			
		}while(!done ||  i < getPizzas().length);
		
		return true;
	}
}
