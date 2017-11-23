package fr.pizzeria.model;

import java.lang.reflect.Field;

/**
 * Cette classe permet de repr�senter une pizza
 * 
 * @author myR3po
 *
 */
public class Pizza {
	
	private static int count; //attribue la valeur de l'id 
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
			if(field.isAnnotationPresent(ToString.class)) {
				Object value = null;
				try {
					value = field.get(this);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				ToString ts = (ToString) field.getAnnotation(ToString.class);
				stringBuilder.append(field.getName() + ":");
				if(value != null && value instanceof String && ts.uppercase()) {
					stringBuilder.append(((String) value).toUpperCase());
				}
				else {
					stringBuilder.append(value);
				}
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}
	
}
