package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Cette classe permet à l'utilisateur de supprimer un objet Pizza
 * 
 * @author myR3po
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu{

	private Scanner scanner;
	private IPizzaDao pizzaDao;
	
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "4. Supprimer une pizza";
		this.pizzaDao = pizzaDao;
		this.setScanner(scanner);
	}


	public boolean execute() throws DeletePizzaException {
		String code = null;
		boolean done = false ;
		
		for(Pizza pizza : this.getPizzaDao().findAllPizzas()) {
			if(pizza != null) {
				LOGGER.info(pizza.toString());
			}
		}

		LOGGER.info("\nVeuillez choisir le code de la pizza à supprimer");
		LOGGER.info("(99 pour abandonner)");
		code = scanner.nextLine();
		
		if (!code.equals("99")) {
			
			if(code.trim().length() != 3) {
				throw new DeletePizzaException("Invalid code");
			}
			
			done = this.getPizzaDao().deletePizza(code.toUpperCase());
			
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
