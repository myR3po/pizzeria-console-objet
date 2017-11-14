package fr.pizzeria.ihm;

public abstract class OptionMenu {
	
	protected String libelle;
	
	public String getLibelle() {
		return libelle;
	}

	public abstract boolean execute();

}
