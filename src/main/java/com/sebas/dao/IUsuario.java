package com.sebas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sebas.model.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer>{

}
