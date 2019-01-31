package transacciones.spring.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import modelo.Persona;

public interface PersonaMapper {

	Persona getPersona(@Param("idPersona") Integer id);
}
