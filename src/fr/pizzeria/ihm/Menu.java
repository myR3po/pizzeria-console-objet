package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class Menu {
	
	private OptionMenu[] actions;
	private final String TITLE = "***** Pizzeria Administration *****\r\n";
	
	private Scanner questionUser;
	private Pizza[] pizzaArray ;
	
	public Menu() {
		this.questionUser = new Scanner(System.in);
		pizzaArray = new Pizza[100];
		actions = new OptionMenu[4];
		init();
		addOptionMenu();
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
	
	private void addOptionMenu() {
		getActions()[0] = new ListerPizzasOptionMenu(pizzaArray);
		getActions()[1] = new AjouterPizzaOptionMenu(pizzaArray, questionUser);
		getActions()[2] = new ModifierPizzaOptionMenu(pizzaArray, questionUser);
		getActions()[3] = new SupprimerPizzaOptionMenu(pizzaArray, questionUser);
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
			switch(choice) {
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
