package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenu extends OptionMenu{

	private Scanner scanner;
	private IPizzaDao pizzaDao;
	
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "4. Supprimer une pizza\r";
		this.pizzaDao = pizzaDao;
		this.setScanner(scanner);
	}

	public boolean execute() throws DeletePizzaException {
		String code = null;
		boolean done = false ;
		
		for(Pizza pizza : this.getPizzaDao().findAllPizzas()) {
			if(pizza != null) {
				System.out.println(pizza);
			}
		}
		System.out.println();

		System.out.println("Veuillez choisir le code de la pizza à supprimer");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();
		
		if (!code.equals("99")) {
			
			if(code.trim().length() != 3) {
				throw new DeletePizzaException("Invalid code");
			}
			
			done = this.getPizzaDao().deletePizza(code);
			
			if (!done) {
				throw new DeletePizzaException("Pizza not found");
			}
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
