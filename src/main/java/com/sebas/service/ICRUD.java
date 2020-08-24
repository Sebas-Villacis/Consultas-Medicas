package com.sebas.service;

import java.util.List;

import javassist.NotFoundException;

public interface ICRUD<T>{
	List<T> findAll();

	T create(T obj);

	T find(Integer id)throws NotFoundException;

	T update(T obj);

	int delete(Integer id);
}
