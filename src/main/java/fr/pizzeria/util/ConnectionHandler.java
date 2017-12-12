package fr.pizzeria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.PizzeriaConnectionException;

public class ConnectionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHandler.class);	
	private static final String FILE_PROPERTIES = "database";
	
	private Connection connection;
	private static ConnectionHandler connectionHandler;
	
	private ConnectionHandler() {
		init();
	}
	
	public static ConnectionHandler getInstance() {
		if(connectionHandler == null) {
			connectionHandler = new ConnectionHandler();
		}
		return connectionHandler;
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
			throw new PizzeriaConnectionException(e.getMessage(),e);
		}	
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
	}

	public void closeStatementAndResultSet(Statement statement, ResultSet resultset) {
		closeResultSet(resultset);
		closeStatement(statement);
	}
		
	public void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
	}

	public void closeResultSet(ResultSet resultset) {
		if(resultset != null) {
			try {
				resultset.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
	}

}
