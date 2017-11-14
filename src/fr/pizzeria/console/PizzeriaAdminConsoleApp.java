package fr.pizzeria.console;

import fr.pizzeria.ihm.Menu;

public class PizzeriaAdminConsoleApp {
	
	private Menu menu;

	public PizzeriaAdminConsoleApp() {
		menu = new Menu();
	}
<<<<<<< HEAD
			
=======
	
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
		Pizza pizza = null;
		String code = null;
		display();
		System.out.println("Veuillez choisir le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		code = questionUser.nextLine();
		
		if(!code.equals("99")) {
			pizza = search(code);
			if(pizza != null) {
				edit(pizza);
			}else {
				System.out.println("Nous n'avons pas cette pizza.");
			}
		}
	}
	
	private Pizza search(String code) {
		int i = 0;
		Pizza pizza = null;
		boolean find = false;
		
		do {
			if(code.equals(pizzaArray[i].getCode())) {
				find = true;
				pizza = pizzaArray[i];
			}
			i++;
		}while(!find || i < pizzaArray.length);
		
		return pizza;
	}
	
	private void delete() {
		int i = 0;
		String code = null;
		
		display();
		System.out.println("Veuillez choisir la pizza à supprimer");
		System.out.println("(99 pour abandonner)");
		code = questionUser.nextLine();
		
		if(!code.equals("99")) {
			Pizza pizza = search(code);
			if(pizza != null) {
				Pizza[] pizzaArrayTemp = pizzaArray.clone();
				pizzaArray = new Pizza[pizzaArray.length - 1];
				 
				i = 0;
				for(Pizza p : pizzaArrayTemp) {
					if(!p.equals(pizza)) {
						pizzaArray[i] = p;
						i++;
					}
				}
			}else {
				System.out.println("Pizza non trouvée");
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
		
>>>>>>> branch 'master' of https://github.com/myR3po/pizzeria-console-objet.git
	public void launch() {
		menu.afficher();
	}
	
	public static void main(String[] args) {
		PizzeriaAdminConsoleApp pizzeriaAdminConsoleApp = new PizzeriaAdminConsoleApp();
		pizzeriaAdminConsoleApp.launch();		
	}
}