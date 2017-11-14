package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao{

	private Pizza[] pizzaArray ;
	
	public PizzaDaoImpl() {
		pizzaArray = new Pizza[100];
		init();
	}
	
	private void init() {		
		pizzaArray[0] = new Pizza("PEP", "Pépéroni", 12.50);
		pizzaArray[1] = new Pizza("MAR", "Margherita", 14.00);
		pizzaArray[2] = new Pizza("REIN", "La Reine", 11.50);
		pizzaArray[3] = new Pizza("FRO", "Les 4 fromages", 12.00);
		pizzaArray[4] = new Pizza("CAN", "La cannibale",12.50);
		pizzaArray[5] = new Pizza("SAV", "La savoyarde", 13.00);
		pizzaArray[6] = new Pizza("ORI", "L\'orientale", 13.50);
		pizzaArray[7] = new Pizza("IND", "L\'indienne",14.00);
	}

	@Override
	public Pizza[] findAllPizzas() {
		return this.getPizzaArray();
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		int i = 0;
		boolean done = false;
		
		do {
			
			if(this.getPizzaArray()[i] == null) {
				this.getPizzaArray()[i] = pizza;
				done = true;
			}
			i++;
			
		}while(!done &&  i < this.getPizzaArray().length);
		
		return done;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		boolean done = false;
		int i=0;
		do {
			if(getPizzaArray()[i] != null && codePizza.equals(this.getPizzaArray()[i].getCode())) {
				done = true;
				this.getPizzaArray()[i] = pizza;
			}
			i++;
		}while(!done || i < this.getPizzaArray().length);
		
		return done;
	}

	@Override
	public boolean deletePizza(String codePizza) {
			int i = 0;
			boolean done = false;
			
			do {
				if(getPizzaArray()[i] != null && codePizza.equals(this.getPizzaArray()[i].getCode())) {
					done = true;
					getPizzaArray()[i] = null;
				}
				i++;
			}while(!done || i < this.getPizzaArray().length);
			
		return done;
	}
	
	public Pizza[] getPizzaArray() {
		return pizzaArray;
	}

	public void setPizzaArray(Pizza[] pizzaArray) {
		this.pizzaArray = pizzaArray;
	}

}
