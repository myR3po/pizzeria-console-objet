package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.util.ConnectionHandler;
/**
 * Cette classe est l'implémentation du dao
 * 
 * Elle realise le CRUD à partir d'une base de données
 * 
 * @author myR3po
 * 
 * @see IPizzaDao
 *
 */
public class PizzaDaoJdbc implements IPizzaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaDaoJdbc.class);
	
	private ConnectionHandler connectionHandler;
	
	protected Connection getConnection() {
		return this.connectionHandler.getConnection();
	}
	
	public void closeConnection() {
		this.connectionHandler.closeConnection();
	}
	
	public PizzaDaoJdbc() {
		connectionHandler = ConnectionHandler.getInstance();
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzas = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			
			String query = "SELECT CODE, NAME, PRICE, CATEGORIE FROM pizza;";
			preparedStatement = this.getConnection().prepareStatement(query);
			resultset = preparedStatement.executeQuery(query);
			pizzas = new ArrayList<Pizza>();
			while(resultset.next()) {
				pizzas.add(new Pizza(resultset.getString("CODE"), resultset.getString("NAME"), CategoryPizza.valueOf(resultset.getString("CATEGORIE").toUpperCase()), resultset.getDouble("PRICE")));
			}
			connectionHandler.closeStatementAndResultSet(preparedStatement, resultset);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			connectionHandler.closeStatementAndResultSet(preparedStatement, resultset);
		}
		
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		PreparedStatement preparedStatement = null;
		boolean saved = false;
		try {
			
			String query = "INSERT INTO pizza (CODE, NAME, CATEGORIE, PRICE) VALUES (?,?,?,?);";
			preparedStatement = this.getConnection().prepareStatement(query);
			
			preparedStatement.setString(1, pizza.getCode());
			preparedStatement.setString(2,pizza.getName());
			preparedStatement.setString(3,pizza.getCategory().getValue());
			preparedStatement.setDouble(4,pizza.getPrice());
			
			saved = preparedStatement.executeUpdate() == 1? true : false;
			connectionHandler.closeStatement(preparedStatement);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			connectionHandler.closeStatement(preparedStatement);
		}
		return saved;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		PreparedStatement preparedStatement = null;
		boolean saved = false;
		try {
			
			String query1 = "UPDATE pizza SET CODE=?, NAME=?, CATEGORIE=?, PRICE=? WHERE CODE=? ;";
			preparedStatement = this.getConnection().prepareStatement(query1);
			
			preparedStatement.setString(1, pizza.getCode());
			preparedStatement.setString(2, pizza.getName());
			preparedStatement.setString(3, pizza.getCategory().getValue());
			preparedStatement.setDouble(4, pizza.getPrice());
			preparedStatement.setString(5, codePizza);
			
			saved = preparedStatement.executeUpdate() == 1? true : false;
			connectionHandler.closeStatement(preparedStatement);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			connectionHandler.closeStatement(preparedStatement);
		}
		return saved;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		PreparedStatement preparedStatement = null;
		boolean saved = false;
		try {
			
			String query1 = "DELETE FROM pizza WHERE CODE = ?;";
			preparedStatement = this.getConnection().prepareStatement(query1);
			
			preparedStatement.setString(1, codePizza);
			saved = preparedStatement.executeUpdate() == 1? true : false;
			connectionHandler.closeStatement(preparedStatement);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			connectionHandler.closeStatement(preparedStatement);
		}
		return saved;
	}

}
