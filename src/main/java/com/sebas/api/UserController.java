package com.sebas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sebas.model.Usuario;
import com.sebas.service.IUsuarioService;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private IUsuarioService service;
	
	@GetMapping(value = "/listar", produces = "application/json" )
	public ResponseEntity<List<Usuario>> listarSpecialidades(){
		return new ResponseEntity<List<Usuario>>(service.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrar",consumes = "application/json" , produces = "application/json" )
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
		return new ResponseEntity<Usuario>(service.create(usuario),HttpStatus.OK);
	}
	
	@PutMapping(value = "/actualizar",consumes = "application/json" , produces = "application/json" )
	public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario){
		return new ResponseEntity<Usuario>(service.update(usuario),HttpStatus.OK);
	}
	
	@PostMapping(value = "/eliminar",consumes = "application/json" , produces = "application/json" )
	public ResponseEntity<Integer> eliminar(@RequestBody Usuario usuario){
		int rpta =0;
		rpta = service.delete(usuario.getId());
		return new ResponseEntity<Integer>(service.delete(rpta),HttpStatus.OK);
	}
}
