package fr.pizzeria.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJdbcTest {

	static PizzaDaoJdbc pizzaDaoJdbc;
	Pizza pizza;

	@BeforeClass
	public static void setupClass() {
		pizzaDaoJdbc = new PizzaDaoJdbc();
	}
	
	@AfterClass
	public static void tearDownClass() {
		clearBase();
		pizzaDaoJdbc.closeConnection();
		pizzaDaoJdbc = null;
	}
	
	
	@Before
	public void setup() {
		pizza = new Pizza("TES", "TEST", CategoryPizza.POISSON, 10.5);
	}
	
	@After
	public void tearDown() {
		clearBase();
		pizza = null;
	}
	
	@Test
	public void testFindAllPizzas() {
		initBaseForFindAllPizzasTest();
		List<Pizza> pizzas = pizzaDaoJdbc.findAllPizzas();
		assertEquals(8, pizzas.size());

	}

	@Test
	public void testSaveNewPizza() {
		boolean isSaved = pizzaDaoJdbc.saveNewPizza(pizza);
		assertTrue("Must be true", isSaved);
	}

	@Test
	public void testUpdatePizza() {
		insertAPizza();
		pizza.setName("Lool");
		boolean isSaved = pizzaDaoJdbc.updatePizza("TES", pizza);
		assertTrue("Must be true", isSaved);
	}

	@Test
	public void testUpdatePizzaNotSaved() {
		boolean isSaved = pizzaDaoJdbc.updatePizza("zet", pizza);
		assertFalse("Must be False", isSaved);
	}

	@Test
	public void testDeletePizza() {
		insertAPizza();
		boolean isSaved = pizzaDaoJdbc.deletePizza("TES");
		assertTrue("Must be True", isSaved);
	}

	private void initBaseForFindAllPizzasTest() {

		StringBuilder insert = new StringBuilder();

		insert.append("INSERT INTO pizza (CODE, NAME, CATEGORIE, PRICE) VALUES")
				.append("(\"PEP\", \"Pépéroni\", \"VIANDE\", 12.50),")
				.append("(\"MAR\", \"Margherita\", \"VIANDE\", 14.00),")
				.append("(\"REIN\", \"La Reine\", \"VIANDE\", 11.50),")
				.append("(\"FRO\", \"Les 4 fromages\", \"SANS_VIANDE\", 12.00),")
				.append("(\"CAN\", \"La cannibale\", \"VIANDE\", 12.50),")
				.append("(\"SAV\", \"La savoyarde\", \"VIANDE\", 13.00),")
				.append("(\"ORI\", \"L'orientale\", \"VIANDE\", 13.50),")
				.append("(\"IND\", \"L'indienne\", \"VIANDE\", 14.00);");

		Connection connection = pizzaDaoJdbc.getConnection();

		Statement stmt = null;

		try {

			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			stmt.addBatch("DELETE FROM pizza;");
			stmt.addBatch(insert.toString());

			stmt.executeBatch();
			connection.commit();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void clearBase() {
		Connection connection = pizzaDaoJdbc.getConnection();

		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM pizza;");
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	private void insertAPizza() {
		Connection connection = pizzaDaoJdbc.getConnection();

		PreparedStatement pstmt = null;

		try {
			
			String query = "INSERT INTO pizza (CODE, NAME, CATEGORIE, PRICE) VALUES (?,?,?,?);";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, "TES");
			pstmt.setString(2, "TEST");
			pstmt.setString(3, CategoryPizza.POISSON.getValue());
			pstmt.setDouble(4, 10.5);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
