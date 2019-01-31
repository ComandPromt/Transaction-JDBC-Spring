package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	public static Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("e ha producido un error al conectar con la BD");
		}
	}

}
