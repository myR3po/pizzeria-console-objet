package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private Pizza[] pizzas;
	private IPizzaDao pizzaDao;
	
	public ListerPizzasOptionMenu(IPizzaDao pizzaDao) {
		this.libelle = "1. Lister les pizzas\r";
		this.pizzaDao = pizzaDao;
	}

	public boolean execute() {
		for(Pizza pizza : this.getPizzaDao().findAllPizzas()) {
			if(pizza != null) {
				System.out.println(pizza);
			}
		}
		System.out.println();
		
		return true;
	}


	public Pizza[] getPizzas() {
		return pizzas;
	}


	public void setPizzas(Pizza[] pizzas) {
		this.pizzas = pizzas;
	}


	public IPizzaDao getPizzaDao() {
		return pizzaDao;
	}


	public void setPizzaDao(IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
