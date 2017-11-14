package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private Pizza[] pizzas;
	private PizzaDaoImpl pizzaDaoImpl;
	
	public ListerPizzasOptionMenu(PizzaDaoImpl pizzaDaoImpl) {
		this.libelle = "1. Lister les pizzas\r";
		this.setPizzaDaoImpl(pizzaDaoImpl);
	}

	public boolean execute() {
		for(Pizza pizza : this.getPizzaDaoImpl().findAllPizzas()) {
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


	public PizzaDaoImpl getPizzaDaoImpl() {
		return pizzaDaoImpl;
	}


	public void setPizzaDaoImpl(PizzaDaoImpl pizzaDaoImpl) {
		this.pizzaDaoImpl = pizzaDaoImpl;
	}
}
