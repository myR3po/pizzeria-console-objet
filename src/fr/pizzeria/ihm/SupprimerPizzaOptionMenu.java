package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenu extends OptionMenu{
	
	private Pizza[] pizzas;
	private Scanner scanner;
	private PizzaDaoImpl pizzaDaoImpl;
	
	public SupprimerPizzaOptionMenu(PizzaDaoImpl pizzaDaoImpl, Scanner scanner) {
		this.libelle = "4. Supprimer une pizza\r";
		this.pizzaDaoImpl = pizzaDaoImpl;
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
	
	public PizzaDaoImpl getPizzaDaoImpl() {
		return pizzaDaoImpl;
	}

	public void setPizzaDaoImpl(PizzaDaoImpl pizzaDaoImpl) {
		this.pizzaDaoImpl = pizzaDaoImpl;
	}

	public boolean execute() {
		String code = null;
		boolean done = false ;

		System.out.println("Veuillez choisir la pizza à supprimer");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			
			done = this.getPizzaDaoImpl().deletePizza(code);
			
			if (!done) {
				System.out.println("Pizza non trouvée");
			}
		}
		
		return done;
	}
}
