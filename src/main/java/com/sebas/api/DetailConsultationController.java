package com.sebas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sebas.model.DetailConsultation;
import com.sebas.service.IDetailConsultationService;

@Controller
@RequestMapping(value = "/detail")
public class DetailConsultationController {

	@Autowired
	private IDetailConsultationService service;
	
	@PostMapping(value = "/registrar")
	public void registrar(DetailConsultation detail){
		service.create(detail);
	}
	
	@PostMapping(value = "/actualizar" )
	public void actualizar(DetailConsultation detail){
		service.update(detail);
	}
	
	@GetMapping(value = "/eliminar/{id}")
	public int eliminar(@PathVariable(value="id")Integer idDetail){
		int rpta = service.delete(idDetail);
		if(rpta > 0) {
			return 1;
		}
		return 0;
	}
	
}
