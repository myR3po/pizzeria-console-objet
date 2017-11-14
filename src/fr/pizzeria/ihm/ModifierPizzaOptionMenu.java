package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UpdatePizzaException;

public class ModifierPizzaOptionMenu extends OptionMenu {
	
	private Pizza[] pizzas;
	private Scanner scanner;
	private PizzaDaoImpl pizzaDaoImpl;
	
	public ModifierPizzaOptionMenu(PizzaDaoImpl pizzaDaoImpl, Scanner scanner) {
		this.libelle = "3. Mettre à jour une pizza\r";
		this.setScanner(scanner);
		this.pizzaDaoImpl = pizzaDaoImpl;
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

	public boolean execute() throws UpdatePizzaException {
		Pizza pizza = null;
		String code = null;
		boolean done;
		
		System.out.println("Veuillez choisir le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			pizza = new Pizza();
			
			System.out.println("Veuillez saisir le code");
			pizza.setCode(scanner.nextLine());

			System.out.println("Veuillez saisir le nom (sans espace)");
			pizza.setName(scanner.nextLine());
			
			System.out.println("Veuillez saisir le prix");
			pizza.setPrice(Double.parseDouble(scanner.nextLine()));
			
			done = this.getPizzaDaoImpl().updatePizza(code, pizza);
		
		if(!done){
			throw new UpdatePizzaException("Nous n'avons pas cette pizza.");
		}
		
	}
		
		return true;

	}
}
