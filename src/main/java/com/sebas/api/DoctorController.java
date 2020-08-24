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

import com.sebas.model.Doctor;
import com.sebas.service.IDetailConsultationService;
import com.sebas.service.IDoctorService;
import com.sebas.service.IMedicalConsultationService;
import com.sebas.service.ISpecialtyService;
import com.sebas.util.ConsultDetail;
import com.sebas.util.EliminarResponse;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;

	@Autowired
	private ISpecialtyService specialtyService;
	
	@Autowired
	private IMedicalConsultationService consultService;
	
	@Autowired
	private IDetailConsultationService detailService;

	@GetMapping(value = "/listar")
	public List<Doctor> listarDoctores() {
		return service.findAll();
	}

	@PostMapping(value = "/registrar")
	public ModelAndView registrar(Doctor doctor) {
		service.create(doctor);
		return paginaDoctors();
	}

	@PostMapping(value = "/actualizar")
	public ModelAndView actualizar(Doctor doctor) {

		service.update(doctor);
		return paginaDoctors();
	}

	@GetMapping(value = "/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminar(@PathVariable(value = "id") Integer idDoctor) {
		ResponseEntity<EliminarResponse> response = null;
		int  rptadetail= -1;
		List<ConsultDetail>consultas = consultService.getAllByIdDoctor(idDoctor);
		if(consultas.isEmpty()) {
			int rpta = service.delete(idDoctor);
			if(rpta > 0) {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"), HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"), HttpStatus.EXPECTATION_FAILED);
			}
		}else {
			for (ConsultDetail consultDetail : consultas) {
				rptadetail = detailService.delete(consultDetail.getIdDetail());
			}
			if (rptadetail > 0) {
				
					consultas.forEach(con->consultService.delete(con.getIdConsult()));
				
				int rpta = service.delete(idDoctor);
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
	public ModelAndView paginaDoctors() {
		ModelAndView model = new ModelAndView();
		model.addObject("doctorList", listarDoctores());
		model.setViewName("doctor/doctors");
		return model;
	}

	@GetMapping(value = { "/add" })
	public ModelAndView addDoctors() {
		ModelAndView model = new ModelAndView();
		model.addObject("specialtyList", specialtyService.findAll());
		model.addObject("newDoctor", new Doctor());
		model.setViewName("doctor/add-doctor");
		return model;
	}

	@GetMapping(value = { "/edit/{id}" })
	public ModelAndView editDoctors(@PathVariable(value = "id") Integer idDoctor) throws NotFoundException {
		ModelAndView model = new ModelAndView();
		model.addObject("specialtyList", specialtyService.findAll());
		model.addObject("doctorEdit", service.find(idDoctor));
		model.setViewName("doctor/edit-doctor");
		return model;
	}
}
