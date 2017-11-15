package fr.pizzeria.model;

import java.lang.reflect.Field;

public class Pizza {

	private static int count;
	private int id;
	@ToString(uppercase = true)
	private String code;
	@ToString
	private String name;
	@ToString
	private double price;
	@ToString(uppercase = true)
	private CategoryPizza category;
	
	public Pizza() {
		
	}
	
	public Pizza(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.id = Pizza.count++;
	}
	
	public Pizza(String code, String name, CategoryPizza category, double price) {
		this(code, name, price);
		this.category  = category; 
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
	
	public CategoryPizza getCategory() {
		return category;
	}

	public void setCategory(CategoryPizza category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		for(Field field : this.getClass().getDeclaredFields()) {
			ToString ts = (ToString) field.getAnnotation(ToString.class);
			if(ts != null) {
				switch(field.getName()) {
				case "id":
					stringBuilder.append(id);
					stringBuilder.append(" : ");
					break;
				case "code":
					if(ts.uppercase()) {
						stringBuilder.append(code.toUpperCase());
					}
					else {
						stringBuilder.append(code);
					}
					stringBuilder.append(" -> ");
					break;
				case "name":
					if(ts.uppercase()) {
						stringBuilder.append(name.toUpperCase());
					}
					else {
						stringBuilder.append(name);
					}
					break;
				case "price":
					stringBuilder.append(" (");
					stringBuilder.append(price);
					stringBuilder.append("\u20ac)");
					break;
				case "category":
					stringBuilder.append("\n");
					if(ts.uppercase()) {
						stringBuilder.append(category.getValue().toUpperCase());
					}
					else {
						stringBuilder.append(category.getValue());
					}
					break;
				}
			}
		}

		return stringBuilder.toString();
	}
	
}
