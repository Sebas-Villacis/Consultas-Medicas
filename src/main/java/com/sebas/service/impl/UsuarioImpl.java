package com.sebas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebas.dao.IUsuario;

import com.sebas.model.Usuario;
import com.sebas.service.IUsuarioService;

import javassist.NotFoundException;
@Service
public class UsuarioImpl implements IUsuarioService{

	@Autowired
	private IUsuario dao;
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Usuario create(Usuario obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public Usuario find(Integer id) throws NotFoundException {
		Optional<Usuario> usuario = dao.findById(id);
	    return usuario.orElseThrow(
	        () ->  new NotFoundException("Unable to get User with Code = " + id)
	    );  
	}

	@Override
	public Usuario update(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer id) {
		int rpta =0;
		try {
			dao.deleteById(id);
			rpta=1;
		} catch (Exception e) {
			rpta =0;
		}
				
		return rpta;
		
	}

	
}
