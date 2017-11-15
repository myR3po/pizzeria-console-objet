package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao{

	private List<Pizza> pizzas ;
	
	public PizzaDaoImpl() {
		pizzas = new ArrayList<Pizza>();
		init();
	}
	
	private void init() {		
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "Les 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale",12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L\'orientale", 13.50));
		pizzas.add(new Pizza("IND", "L\'indienne",14.00));
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return this.getPizzas();
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		return this.getPizzas().add(pizza);
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		boolean done = false;
		int i=0;
		do {
			
			if(codePizza.equals(this.getPizzas().get(i).getCode())) {
				done = true;
				this.getPizzas().set(i, pizza);
			}
			i++;
		}while(!done && i < this.getPizzas().size());
		
		return done;
	}

	@Override
	public boolean deletePizza(String codePizza) {
			int i = 0;
			boolean done = false;
			
			do {
				if(codePizza.equals(this.getPizzas().get(i).getCode())) {
					done = true;
					this.getPizzas().remove(i);
				}
				i++;
			}while(!done && i < this.getPizzas().size());
			
		return done;
	}
	
	private List<Pizza> getPizzas() {
		return pizzas;
	}


}
