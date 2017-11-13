package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {
	private Scanner questionUser;
	private Pizza[] pizzaArray ;
	
	public PizzeriaAdminConsoleApp() {
		this.questionUser = new Scanner(System.in);
		init();
	}
	
	private void init() {
		pizzaArray = new Pizza []{ 
				new Pizza("PEP", "Pépéroni", 12.50),
				new Pizza("MAR", "Margherita", 14.00),
				new Pizza("REIN", "La Reine", 11.50),
				new Pizza("FRO", "Les 4 fromages", 12.00),
				new Pizza("CAN", "La cannibale",12.50),
				new Pizza("SAV", "La savoyarde", 13.00),
				new Pizza("ORI", "L\'orientale", 13.50),
				new Pizza("IND", "L\'indienne",14.00)
		};
	}
	
	private void display() {
		for(Pizza pizza : pizzaArray) {
			System.out.println(pizza);
		}
		System.out.println();
	}

//	TODO (me): refactor this method 
	private void add(){
		System.out.println("Veuillez saisir le code");
		String code = questionUser.nextLine();

		System.out.println("Veuillez saisir le nom (sans espace)");
		String name = questionUser.nextLine();
		
		System.out.println("Veuillez saisir le prix");
		double price = Double.parseDouble(questionUser.nextLine());
		
		Pizza[] pizzaArrayTemp = pizzaArray.clone();
		pizzaArray = new Pizza[pizzaArray.length + 1];
		
		for(int i = 0; i < pizzaArrayTemp.length; i++) {
			pizzaArray[i] = pizzaArrayTemp[i];
		}

		pizzaArray[pizzaArrayTemp.length] = new Pizza(code, name, price);
	}
	
	
	
	private int menu() {
		System.out.println("***** Pizzeria Administration *****\r\n" + 
				"1. Lister les pizzas\r\n" + 
				"2. Ajouter une nouvelle pizza\r\n" + 
				"3. Mettre à jour une pizza\r\n" + 
				"4. Supprimer une pizza\r\n" + 
				"99. Sortir");
		
		return Integer.parseInt(questionUser.nextLine());
	}
		
	public void launch() {
		boolean want = true;
		while(want) {
			switch(menu()) {
			case 1:
				display();
				break;
			case 2:
				add();
				break;
			case 3:
				System.out.println("modif");
				break;
			case 4:
				System.out.println("delete");
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
	
	public static void main(String[] args) {
				
		PizzeriaAdminConsoleApp pizzeriaAdminConsoleApp = new PizzeriaAdminConsoleApp();
		pizzeriaAdminConsoleApp.launch();
			
	}

}
