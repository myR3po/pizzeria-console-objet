package fr.pizzeria.ihm;

import fr.pizzeria.exception.StockageException;

public abstract class OptionMenu {
	
	protected String libelle;
	
	public String getLibelle() {
		return libelle;
	}

	public abstract boolean execute() throws StockageException;

}
