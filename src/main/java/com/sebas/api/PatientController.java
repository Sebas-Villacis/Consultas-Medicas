package com.sebas.api;

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

import com.sebas.model.Patient;
import com.sebas.service.IDetailConsultationService;
import com.sebas.service.IMedicalConsultationService;
import com.sebas.service.IPatientService;
import com.sebas.util.ConsultDetail;
import com.sebas.util.EliminarResponse;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

	@Autowired
	private IPatientService service;
	
	@Autowired
	private IMedicalConsultationService consultService;
	
	@Autowired
	private IDetailConsultationService detailService;

	@GetMapping(value = "/listar")
	public List<Patient> listarPacientes() {
		return service.findAll();
	}

	@PostMapping(value = "/registrar")
	public ModelAndView registrar(Patient patient) {
		service.create(patient);
		return paginaPatients();
	}

	@PostMapping(value = "/actualizar")
	public ModelAndView actualizar(Patient patient) {
		service.update(patient);
		return paginaPatients();
	}

	@GetMapping(value = "/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminar(@PathVariable(value = "id") Integer idPatient) {
		ResponseEntity<EliminarResponse> response = null;
		int  rptadetail= -1;
		List<ConsultDetail>consultas = consultService.getAllByIdPatient(idPatient);
		if(consultas.isEmpty()) {
			int rpta = service.delete(idPatient);
			if(rpta > 0) {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"), HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"), HttpStatus.EXPECTATION_FAILED);
			}
		}
		else {
			for (ConsultDetail consultDetail : consultas) {
				rptadetail = detailService.delete(consultDetail.getIdDetail());
			}
			if (rptadetail > 0) {
				
				
					consultas.forEach(con-> consultService.delete(con.getIdConsult()))
					;
				
				int rpta = service.delete(idPatient);
				if(rpta > 0) {
					response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"), HttpStatus.OK);
				}
				else {
					response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"), HttpStatus.EXPECTATION_FAILED);
				}
				
			}
			else {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"), HttpStatus.EXPECTATION_FAILED);
			}
		}
		
		return response;
	}

	@GetMapping
	public ModelAndView paginaPatients() {
		ModelAndView model = new ModelAndView();
		model.addObject("patientList", listarPacientes());
		model.setViewName("patient/patients");
		return model;
	}

	@GetMapping(value = { "/add" })
	public ModelAndView addSpecialty() {
		ModelAndView model = new ModelAndView();
		model.addObject("newPatient", new Patient());
		model.setViewName("patient/add-patient");
		return model;
	}

	@GetMapping(value = { "/edit/{id}" })
	public ModelAndView editPatients(@PathVariable(value = "id") Integer idPatient) throws NotFoundException {
		ModelAndView model = new ModelAndView();
		model.addObject("patientEdit", service.find(idPatient));
		model.setViewName("patient/edit-patient");
		return model;
	}
}
