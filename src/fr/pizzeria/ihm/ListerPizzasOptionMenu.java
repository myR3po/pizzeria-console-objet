package fr.pizzeria.ihm;

import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private Pizza[] pizzas;

	public ListerPizzasOptionMenu(Pizza[] pizzas) {
		this.libelle = "1. Lister les pizzas\r";
		this.setPizzas(pizzas);
	}


	public boolean execute() {
		for(Pizza pizza : this.getPizzas()) {
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
}
