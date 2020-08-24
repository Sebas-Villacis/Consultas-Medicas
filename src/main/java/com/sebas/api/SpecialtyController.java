package com.sebas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sebas.model.Specialty;
import com.sebas.service.ISpecialtyService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/specialty")
public class SpecialtyController {

	@Autowired
	private ISpecialtyService service;

	@GetMapping(value = "/listar")
	public List<Specialty> listarSpecialidades() {
		return service.findAll();
	}

	@PostMapping(value = "/registrar")
	public ModelAndView registrar(Specialty specialty) {
		service.create(specialty);
		return paginaSpecialty();
	}

	@PostMapping(value = "/actualizar")
	public ModelAndView actualizar(Specialty specialty) {
		service.update(specialty);
		return paginaSpecialty();
	}

	@PostMapping(value = "/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable(value = "id") Integer idSpecialty) {

		int rpta = service.delete(idSpecialty);
		if (rpta > 0) {
			return new ResponseEntity<String>("Se eliminó correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<String>("No se eliminó", HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping
	public ModelAndView paginaSpecialty() {
		ModelAndView model = new ModelAndView();
		model.addObject("specialtyList", listarSpecialidades());
		model.setViewName("specialty/specialty");
		return model;
	}

	@GetMapping(value = { "/add" })
	public ModelAndView addSpecialty() {
		ModelAndView model = new ModelAndView();
		model.addObject("newSpecialty", new Specialty());
		model.setViewName("specialty/add-specialty");
		return model;
	}

	@GetMapping(value = { "/edit/{id}" })
	public ModelAndView editSpecialty(@PathVariable(value = "id") Integer idSpecialty) throws NotFoundException {
		ModelAndView model = new ModelAndView();
		model.addObject("specialtyEdit", service.find(idSpecialty));
		model.setViewName("specialty/edit-specialty");
		return model;
	}
}
