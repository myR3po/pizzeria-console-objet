package fr.pizzeria.console;

import fr.pizzeria.ihm.Menu;

/**
 * 
 * @author myR3po
 *
 */
public class PizzeriaAdminConsoleApp {

	private Menu menu;

	public PizzeriaAdminConsoleApp() {
		menu = new Menu();
	}

	public void launch() {
		menu.afficher();
	}
	
	public static void main(String[] args) {
		PizzeriaAdminConsoleApp pizzeriaAdminConsoleApp = new PizzeriaAdminConsoleApp();
		pizzeriaAdminConsoleApp.launch();
	}
}