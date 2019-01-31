package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import database.Database;

public class PersonaDao {

	public void insertarPersona(int id) throws Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = Database.getConexion();
			statement = connection.createStatement();

			statement.executeUpdate(
					"INSERT INTO persona (id, nombre, apellidos) VALUES (" + id + ", 'Miguel', 'Sanchez')");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			statement.close();
			connection.close();
		}
	}

	public void modificarPersona(int id, String apellidos) throws Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = Database.getConexion();
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			statement.executeUpdate("UPDATE persona SET apellidos = '" + apellidos + "' WHERE id = " + id);

			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			statement.close();
			connection.close();
		}
	}

	public String consultarPersona(int id) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = Database.getConexion();
			statement = connection.createStatement();

			result = statement.executeQuery("SELECT apellidos FROM persona WHERE id = " + id);
			result.next();

			return result.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			result.close();
			statement.close();
			connection.close();
		}
	}

	@Transactional
	public void insertarPersona2(int id, DataSource dataSource) {
		new JdbcTemplate(dataSource)
				.execute("INSERT INTO persona (id, nombre, apellidos) VALUES (" + id + ", 'Miguel', 'Sanchez')");
	}

	@Transactional
	public void modificarPersona2(int id, String apellidos, DataSource dataSource) {
		new JdbcTemplate(dataSource).execute("UPDATE persona SET apellidos = '" + apellidos + "' WHERE id = " + id);
	}

	public void crearPersona(int id, DataSource dataSource, String apellidos) {
		this.insertarPersona2(id, dataSource);
		this.modificarPersona2(id, apellidos, dataSource);
	}

	public static void main(String[] args) {
		ApplicationContext contexto = new ClassPathXmlApplicationContext("spring-configuracion/config-spring.xml");

		PersonaDao dao = new PersonaDao();
		dao.insertarPersona2(3, (DataSource) contexto.getBean("dualDataSource"));
	}
}
