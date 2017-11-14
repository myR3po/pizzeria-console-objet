package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenu extends OptionMenu{
	
	private Pizza[] pizzas;
	private Scanner scanner;
	
	public SupprimerPizzaOptionMenu(Pizza[] pizzas, Scanner scanner) {
		this.libelle = "4. Supprimer une pizza\r";
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
		int i = 0;
		String code = null;

		System.out.println("Veuillez choisir la pizza à supprimer");
		System.out.println("(99 pour abandonner)");
		code = scanner.nextLine();

		if (!code.equals("99")) {
			
			boolean find = false;
			
			do {
				if(pizzas[i] != null && code.equals(this.pizzas[i].getCode())) {
					find = true;
					pizzas[i] = null;
				}
				i++;
			}while(!find || i < this.getPizzas().length);
			
			if (!find) {
				System.out.println("Pizza non trouvée");
			}
		}
		
		return true;
	}
}
