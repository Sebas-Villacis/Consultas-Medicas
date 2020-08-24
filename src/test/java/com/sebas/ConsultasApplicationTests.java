package com.sebas;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.sebas.model.Usuario;
import com.sebas.service.IUsuarioService;


@SpringBootTest
class ConsultasApplicationTests {

	@Autowired
	private IUsuarioService service;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Test
	public void crearUsuario() {
		Usuario us = new Usuario();
		us.setNombre("Andres");
		us.setClave(bcrypt.encode("123"));
//		us.setTipo("ROLE_ADMIN");
		us.setTipo("ROLE_USER");
		us.setEstado("1");

		Usuario retorno = service.create(us);
		assertTrue(retorno.getClave().equalsIgnoreCase((us.getClave())));

	}

}
