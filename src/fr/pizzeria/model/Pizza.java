package fr.pizzeria.model;

public class Pizza {

	private static int count;
	private int id;
	private String code;
	private String name;
	private double price;
	
	public Pizza(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.id = Pizza.count++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code + " -> " + name + " (" + price + "\u20ac)";
	}
	
	
}
