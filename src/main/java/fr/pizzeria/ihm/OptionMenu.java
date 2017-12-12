package fr.pizzeria.ihm;

import fr.pizzeria.exception.StockageException;

/**
 * Cette classe represente une option du menu.
 * 
 * Elle se charge d'executer l'action
 * 
 * @author myR3po
 *
 */
public abstract class OptionMenu {
	
	protected String libelle;
	
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Cette méthode permet d'effectuer d'executer l'action
	 *  
	 * @return true si l'action a bien été effectuée, false sinon
	 * @throws StockageException
	 */
	
	public abstract boolean execute() throws StockageException;

}
