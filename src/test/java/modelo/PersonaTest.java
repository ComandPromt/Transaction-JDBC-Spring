package modelo;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-configuracion/config-spring.xml" })
@TransactionConfiguration(transactionManager = "dualTransactionManager")
public class PersonaTest {

	@Autowired
	private ApplicationContext contexto;

	@Test
	public void deberiaInsertarUnaPersona() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.insertarPersona(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaModificarUnaPersona() {
		PersonaDao dao = new PersonaDao();

		try {
			dao.modificarPersona(1, "prueba");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(dao.consultarPersona(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaInsertarUnaPersonaDos() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.insertarPersona2(1, (DataSource) contexto.getBean("dualDataSource"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaCrearUnaPersona() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.crearPersona(2, (DataSource) contexto.getBean("dualDataSource"), "hola");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
