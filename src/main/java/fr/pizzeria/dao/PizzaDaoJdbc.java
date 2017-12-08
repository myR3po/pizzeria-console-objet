package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;
/**
 * Cette classe est l'implémentation du dao
 * 
 * @author myR3po
 * 
 * @see IPizzaDao
 *
 */
public class PizzaDaoJdbc implements IPizzaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaDaoJdbc.class);
	
	private static final String FILE_PROPERTIES = "database";
	private Connection connection;
	
	protected Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		if(this.getConnection() != null) {
			try {
				this.getConnection().close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
	}
	
	public PizzaDaoJdbc() {
		init();
	}

	private void init() {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_PROPERTIES);
		String driver = resourceBundle.getString("database.driver");
		String url = resourceBundle.getString("database.url");
		String user = resourceBundle.getString("database.user");
		String password = resourceBundle.getString("database.password");
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzas = null;
		PreparedStatement pstmt = null;
		try {
			
			String query = "SELECT CODE, NAME, PRICE, CATEGORIE FROM pizza;";
			pstmt = this.getConnection().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			pizzas = new ArrayList<Pizza>();
			while(rs.next()) {
				pizzas.add(new Pizza(rs.getString("CODE"), rs.getString("NAME"), CategoryPizza.valueOf(rs.getString("CATEGORIE").toUpperCase()), rs.getDouble("PRICE")));
			}
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage(),e);
				}
			}
		}
		
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		PreparedStatement pstmt = null;
		boolean saved = false;
		try {
			
			String query = "INSERT INTO pizza (CODE, NAME, CATEGORIE, PRICE) VALUES (?,?,?,?);";
			pstmt = this.getConnection().prepareStatement(query);
			
			pstmt.setString(1, pizza.getCode());
			pstmt.setString(2,pizza.getName());
			pstmt.setString(3,pizza.getCategory().getValue());
			pstmt.setDouble(4,pizza.getPrice());
			
			saved = pstmt.executeUpdate() == 1? true : false;
			pstmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage(),e);
				}
			}
		}
		return saved;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		PreparedStatement pstmt = null;
		boolean saved = false;
		try {
			
			String query1 = "UPDATE pizza SET CODE=?, NAME=?, CATEGORIE=?, PRICE=? WHERE CODE=? ;";
			pstmt = this.getConnection().prepareStatement(query1);
			
			pstmt.setString(1, pizza.getCode());
			pstmt.setString(2, pizza.getName());
			pstmt.setString(3, pizza.getCategory().getValue());
			pstmt.setDouble(4, pizza.getPrice());
			pstmt.setString(5, codePizza);
			
			saved = pstmt.executeUpdate() == 1? true : false;
			pstmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage(),e);
				}
			}
		}
		return saved;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		PreparedStatement pstmt = null;
		boolean saved = false;
		try {
			
			String query1 = "DELETE FROM pizza WHERE CODE = ?;";
			pstmt = this.getConnection().prepareStatement(query1);
			
			pstmt.setString(1, codePizza);
			saved = pstmt.executeUpdate() == 1? true : false;
			pstmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(),e);
		}
		finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage(),e);
				}
			}
		}
		return saved;
	}

}
