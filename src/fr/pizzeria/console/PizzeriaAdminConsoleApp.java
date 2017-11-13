package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {
	private Scanner questionUser;
	
	public PizzeriaAdminConsoleApp() {
		this.questionUser = new Scanner(System.in);
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
				System.out.println("liste");
				break;
			case 2:
				System.out.println("add");
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
