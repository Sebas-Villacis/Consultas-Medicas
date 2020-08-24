package com.sebas.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sebas.model.DetailConsultation;
import com.sebas.model.MedicalConsultation;
import com.sebas.service.IDetailConsultationService;
import com.sebas.service.IDoctorService;
import com.sebas.service.IMedicalConsultationService;
import com.sebas.service.IPatientService;
import com.sebas.util.EliminarResponse;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/consultation")
public class MedicalConsultationController {

	@Autowired
	private IMedicalConsultationService service;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private IDetailConsultationService detailService;

	@GetMapping(value = "/listar")
	public List<MedicalConsultation> listarConsultas() {
		return service.findAll();
	}

	@PostMapping(value = "/registrar")
	public ModelAndView registrar(MedicalConsultation consultation, DetailConsultation detail) {
		MedicalConsultation consult = service.create(consultation);
		detail.setId_consultation(consult);
		detailService.create(detail);
		return paginaConsults();
	}

	@PostMapping(value = "/actualizar")
	public ModelAndView actualizar(MedicalConsultation consultation, DetailConsultation detail) {
		service.update(consultation);
		detailService.update(detail);
		return paginaConsults();
	}

	@GetMapping(value = "/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminar(@PathVariable(value = "id") Integer idConsult) throws NotFoundException {
		ResponseEntity<EliminarResponse> response = null;
		DetailConsultation detail = detailService.find(idConsult);
		int rptadetail = detailService.delete(detail.getId());
		if (rptadetail > 0) {
			int rpta = service.delete(idConsult);
			if (rpta > 0) {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"), HttpStatus.OK);
			} else {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"), HttpStatus.EXPECTATION_FAILED);
			}

		} else {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"), HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	@GetMapping
	public ModelAndView paginaConsults() {
		ModelAndView model = new ModelAndView();
		List<MedicalConsultation> lista = new ArrayList<>();
		lista = listarConsultas();
		model.addObject("consultationList", lista);
		model.setViewName("medicalconsultation/medicalconsultations");
		return model;
	}

	@GetMapping(value = { "/add" })
	public ModelAndView addConsult() {
		ModelAndView model = new ModelAndView();
		model.addObject("doctorList", doctorService.findAll());
		model.addObject("patientList", patientService.findAll());
		model.addObject("newConsult", new MedicalConsultation());
		model.addObject("newDetail", new DetailConsultation());
		model.setViewName("medicalconsultation/add-medicalconsultation");
		return model;
	}

	@GetMapping(value = { "/edit/{id}" })
	public ModelAndView editConsult(@PathVariable(value = "id") Integer idConsult) throws NotFoundException {
		ModelAndView model = new ModelAndView();
		model.addObject("doctorList", doctorService.findAll());
		model.addObject("patientList", patientService.findAll());
		model.addObject("consultEdit", service.find(idConsult));
		model.addObject("detailEdit", detailService.find(idConsult));
		model.setViewName("medicalconsultation/edit-medicalconsultation");
		return model;
	}
}
