package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;
/**
 * Cette classe permet de lister tous les objets Pizza
 * 
 * @author myR3po
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {
	
	private IPizzaDao pizzaDao;
	
	public ListerPizzasOptionMenu(IPizzaDao pizzaDao) {
		this.libelle = "1. Lister les pizzas";
		this.pizzaDao = pizzaDao;
	}

	public boolean execute() {
		for(Pizza pizza : this.getPizzaDao().findAllPizzas()) {
			LOGGER.info(pizza.toString());
		}
		
		return true;
	}

	public IPizzaDao getPizzaDao() {
		return pizzaDao;
	}

	public void setPizzaDao(IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
