package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenu extends OptionMenu{
	
	private Pizza[] pizzas;
	private Scanner scanner;
	private IPizzaDao pizzaDao;
	
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "4. Supprimer une pizza\r";
		this.pizzaDao = pizzaDao;
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
	
	public IPizzaDao getPizzaDao() {
		return pizzaDao;
	}

	public void setPizzaDao(IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}

	public boolean execute() throws DeletePizzaException {
		String code = null;
		boolean done = false ;

		System.out.println("Veuillez choisir le code de la pizza à supprimer");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			
			done = this.getPizzaDao().deletePizza(code);
			
			if (!done) {
				throw new DeletePizzaException("Pizza not found");
			}
		}
		
		return done;
	}
}
