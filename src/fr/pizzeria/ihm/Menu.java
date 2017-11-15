package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.StockageException;

public class Menu {
	
	private Map<Integer, OptionMenu> actions;
	private final String TITLE = "***** Pizzeria Administration *****\r\n";
	private PizzaDaoImpl pizzaDaoImpl;
	private Scanner questionUser;
	
	
	public Menu() {
		this.pizzaDaoImpl = new PizzaDaoImpl();
		this.questionUser = new Scanner(System.in);
		actions = new HashMap<Integer, OptionMenu>();
		addOptionMenu();
	}
	
	private void addOptionMenu() {
		getActions().put(1, new ListerPizzasOptionMenu(pizzaDaoImpl));
		getActions().put(2, new AjouterPizzaOptionMenu(pizzaDaoImpl, questionUser));
		getActions().put(3, new ModifierPizzaOptionMenu(pizzaDaoImpl, questionUser));
		getActions().put(4, new SupprimerPizzaOptionMenu(pizzaDaoImpl, questionUser));
	}
	
	
	public void afficher() {

		int choice = 0 ;
		while(choice != 99) {
			
			System.out.println(this.getTitle());
			for(Integer nb : getActions().keySet()) {
				System.out.print(getActions().get(nb).getLibelle());
			}
			System.out.println("99. Sortir");
			choice = Integer.parseInt(questionUser.nextLine());
			try {
				if(choice > 1 && choice < 5){
					this.getActions().get(choice).execute();
				}
				
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Bye...");
		
		if(questionUser != null) {
			questionUser.close();
		}
	}

	public Map<Integer, OptionMenu> getActions() {
		return actions;
	}

	public void setActions(Map<Integer, OptionMenu> actions) {
		this.actions = actions;
	}

	public String getTitle() {
		return TITLE;
	}
}
