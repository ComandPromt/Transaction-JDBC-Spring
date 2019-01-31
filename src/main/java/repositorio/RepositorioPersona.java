package repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import modelo.Persona;
import transacciones.spring.mybatis.mapper.PersonaMapper;

@Repository
public class RepositorioPersona {

	@Autowired
	private PersonaMapper personaMapper;

	public Persona getPersona(Integer id) {
		return this.personaMapper.getPersona(id);
	}

	public static void main(String[] args) {
		ApplicationContext contexto = new ClassPathXmlApplicationContext("spring-configuracion/config-spring.xml");

		RepositorioPersona repo = new RepositorioPersona();

		System.out.println(repo.getPersona(1));
	}
}
