package helpers;

import java.sql.*;

public class DbHelper {

	Connection conn;


	public DbHelper(String dbName) {
		String url = "jdbc:mysql://localhost:3306/"+dbName;
		String username = "jaita";
		String password = "jaita";

		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connessione al database stabilita con successo!");
		} catch (SQLException e) {
			System.err.println("Errore durante la connessione al database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ResultSet executeSelect(String query) {
		try{
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}catch(SQLException e){
			System.err.println("Query errata, messaggio errore: ");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void executeDML(String query) {
		try{
			Statement statement = conn.createStatement();
			statement.execute(query);

		}catch(SQLException e){
			System.err.println("Query errata, messaggio errore: ");
			System.out.println(e.getMessage());
		}
	}
}