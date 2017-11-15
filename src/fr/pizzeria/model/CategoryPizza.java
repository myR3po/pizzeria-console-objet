package fr.pizzeria.model;

public enum CategoryPizza {
	VIANDE ("Viande"),
	POISSON("Poisson"),
	SANS_VIANDE("Sans Viande");
	
	 private String category;
	   
	 CategoryPizza(String category){
	    this.category = category;
	  }

	 public String getValue() {
		 return category;
	 }
}
