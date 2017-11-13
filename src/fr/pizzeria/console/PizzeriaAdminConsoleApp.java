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

	private void add(){
		Pizza pizza = new Pizza();		
		edit(pizza);
		
		Pizza[] pizzaArrayTemp = pizzaArray.clone();
		pizzaArray = new Pizza[pizzaArray.length + 1];
		
		for(int i = 0; i < pizzaArrayTemp.length; i++) {
			pizzaArray[i] = pizzaArrayTemp[i];
		}

		pizzaArray[pizzaArrayTemp.length] = pizza;
	}
	
	private void edit(Pizza pizza) {
		System.out.println("Veuillez saisir le code");
		pizza.setCode(questionUser.nextLine());

		System.out.println("Veuillez saisir le nom (sans espace)");
		pizza.setName(questionUser.nextLine());
		
		System.out.println("Veuillez saisir le prix");
		pizza.setPrice(Double.parseDouble(questionUser.nextLine()));
	}
	
	private void update() {
		int i = 0;
		Pizza pizza = null;
		boolean find = false;
		
		display();
		System.out.println("Veuillez choisir le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		String choice = questionUser.nextLine();
		if(!choice.equals("99")) {
			do {
				if(choice.equals(pizzaArray[i].getCode())) {
					find = true;
					pizza = pizzaArray[i];
				}
				i++;
			}while(!find || i < pizzaArray.length);
			
			if(pizza != null) {
				edit(pizza);
			}else {
				System.out.println("Nous n'avons pas cette pizza.");
			}
		}
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
				update();
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
