package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {
	
	private Pizza[] pizzas;
	private Scanner scanner;
	
	public ModifierPizzaOptionMenu(Pizza[] pizzas, Scanner scanner) {
		this.libelle = "3. Mettre à jour une pizza\r";
		this.setPizzas(pizzas);
		this.setScanner(scanner);
	}

	public Pizza[] getPizzas() {
		return pizzas;
	}

	public void setPizzas(Pizza[] pizzas) {
		this.pizzas = pizzas;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}


	public boolean execute() {
		Pizza pizza = null;
		String code = null;
		
		System.out.println("Veuillez choisir le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			
			boolean find = false;
			int i=0;
			do {
				if(pizzas[i] != null && code.equals(this.getPizzas()[i].getCode())) {
					find = true;
					pizza = this.getPizzas()[i];
				}
				i++;
			}while(!find || i < this.getPizzas().length);
			
			if (pizza != null) {
				
				System.out.println("Veuillez saisir le code");
				pizza.setCode(scanner.nextLine());

				System.out.println("Veuillez saisir le nom (sans espace)");
				pizza.setName(scanner.nextLine());
				
				System.out.println("Veuillez saisir le prix");
				pizza.setPrice(Double.parseDouble(scanner.nextLine()));
				
			} else {
				System.out.println("Nous n'avons pas cette pizza.");
			}
		}
		
		return true;

	}
}
