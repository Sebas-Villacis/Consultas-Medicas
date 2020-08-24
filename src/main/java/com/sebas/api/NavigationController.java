package com.sebas.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sebas.service.IDoctorService;
import com.sebas.service.IMedicalConsultationService;
import com.sebas.service.IPatientService;
import com.sebas.service.ISpecialtyService;

@Controller
public class NavigationController {

	@Autowired
	private IDoctorService doctorService;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private IMedicalConsultationService consultService;
	@Autowired
	private ISpecialtyService specialtyService;
	
	

	@GetMapping(value = { "/", "/principal**" })
	public ModelAndView paginaInicio() {
		ModelAndView model = new ModelAndView();
		model.addObject("titulo", "Spring Boot - Clinical");
		model.setViewName("login");
		return model;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView irDashboard() {
		ModelAndView model = new ModelAndView();
		model.addObject("doctors", doctorService.countDoctors());
		model.addObject("patients", patientService.countPatients());
		model.addObject("consults", consultService.countConsults());
		model.addObject("specialties", specialtyService.countSpecialty());
		model.setViewName("dashboard");

		return model;
	}

	@GetMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Crendenciales incorrectas!");
		}

		if (logout != null) {
			model.addObject("msg", "Sesión cerrada correctamente.");
		}

		model.setViewName("login");

		return model;

	}
	

}
