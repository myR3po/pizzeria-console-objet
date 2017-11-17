package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.util.UserInputHelper;
/**
 * Cette classe permet à l'utilisateur d'ajouter un objet Pizza
 * 
 * @author myR3po
 *
 */
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
		
		UserInputHelper<SavePizzaException> userInputHelper = new UserInputHelper<SavePizzaException>(scanner);
		
		pizza = userInputHelper.getUserInput();
		
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
