package fr.pizzeria.ihm;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;

public abstract class OptionMenu {
	
	protected String libelle;
	
	public String getLibelle() {
		return libelle;
	}

	public abstract boolean execute() throws UpdatePizzaException, DeletePizzaException, SavePizzaException;

}
