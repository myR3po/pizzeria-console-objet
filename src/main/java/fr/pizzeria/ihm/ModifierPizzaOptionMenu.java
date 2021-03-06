package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.util.UserInputHelper;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
/**
 * Cette classe permet à l'utilisateur de modifier un objet Pizza
 * 
 * @author myR3po
 *
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	private Scanner scanner;
	private IPizzaDao pizzaDao;

	public ModifierPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "3. Mettre à jour une pizza";
		this.setScanner(scanner);
		this.pizzaDao = pizzaDao;
	}

	public boolean execute() throws UpdatePizzaException {
		Pizza pizza = null;
		String code = null;
		boolean done;
		
		for(Pizza p : this.getPizzaDao().findAllPizzas()) {
			if(p != null) {
				LOGGER.info(p.toString());
			}
		}

		LOGGER.info("\nVeuillez choisir le code de la pizza à modifier");
		LOGGER.info("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			pizza = new Pizza();
			
			UserInputHelper<UpdatePizzaException> userInputHelper = new UserInputHelper<UpdatePizzaException>(scanner);
			pizza = userInputHelper.getUserInput();
			
			done = this.getPizzaDao().updatePizza(code.toUpperCase(), pizza);

			if (!done) {
				throw new UpdatePizzaException("Nous n'avons pas cette pizza.");
			}

		}

		return true;

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
