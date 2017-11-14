package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;

public class ModifierPizzaOptionMenu extends OptionMenu {

	private Pizza[] pizzas;
	private Scanner scanner;
	private IPizzaDao pizzaDao;

	public ModifierPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		this.libelle = "3. Mettre à jour une pizza\r";
		this.setScanner(scanner);
		this.pizzaDao = pizzaDao;
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
			
			if(pizza.getCode().length() != 3) {
				throw new UpdatePizzaException("Length must equal 3");
			}

			System.out.println("Veuillez saisir le nom (sans espace)");
			pizza.setName(scanner.nextLine());
			
			if(pizza.getName().contains(" ")) {
				throw new UpdatePizzaException("The name must not contain space.");
			}

			System.out.println("Veuillez saisir le prix");
			
			try {
				pizza.setPrice(Double.parseDouble(scanner.nextLine()));
			} catch (NumberFormatException ex) {
				throw new UpdatePizzaException(ex.getMessage());
			}
			
			done = this.getPizzaDao().updatePizza(code, pizza);

			if (!done) {
				throw new UpdatePizzaException("Nous n'avons pas cette pizza.");
			}

		}

		return true;

	}
}
