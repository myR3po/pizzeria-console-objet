package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;

public class Menu {
	
	private OptionMenu[] actions;
	private final String TITLE = "***** Pizzeria Administration *****\r\n";
	private PizzaDaoImpl pizzaDaoImpl;
	private Scanner questionUser;
	
	
	public Menu() {
		this.pizzaDaoImpl = new PizzaDaoImpl();
		this.questionUser = new Scanner(System.in);
		actions = new OptionMenu[4];
		addOptionMenu();
	}
	
	private void addOptionMenu() {
		getActions()[0] = new ListerPizzasOptionMenu(pizzaDaoImpl);
		getActions()[1] = new AjouterPizzaOptionMenu(pizzaDaoImpl, questionUser);
		getActions()[2] = new ModifierPizzaOptionMenu(pizzaDaoImpl, questionUser);
		getActions()[3] = new SupprimerPizzaOptionMenu(pizzaDaoImpl, questionUser);
	}
	
	
	public void afficher() {

		int choice = 0 ; 
		boolean want = true;
		while(want) {
			
			System.out.println(this.getTitle());
			for(OptionMenu optionMenu: getActions()) {
				System.out.print(optionMenu.getLibelle());
			}
			System.out.println("99. Sortir");
			choice = Integer.parseInt(questionUser.nextLine());
			try {
				switch (choice) {
				case 1:
					this.getActions()[0].execute();
					break;
				case 2:
					this.getActions()[0].execute();

					this.getActions()[1].execute();

					break;
				case 3:
					this.getActions()[0].execute();
					this.getActions()[2].execute();
					break;
				case 4:
					this.getActions()[0].execute();
					this.getActions()[3].execute();
					break;
				case 99:
					System.out.println("Bye...");
					want = false;
					break;
				default:
				}
			} catch (UpdatePizzaException | DeletePizzaException | SavePizzaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(questionUser != null) {
			questionUser.close();
		}
	}

	public OptionMenu[] getActions() {
		return actions;
	}

	public void setActions(OptionMenu[] actions) {
		this.actions = actions;
	}

	public String getTitle() {
		return TITLE;
	}

}
