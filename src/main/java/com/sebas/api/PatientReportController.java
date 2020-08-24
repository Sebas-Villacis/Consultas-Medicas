package com.sebas.api;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sebas.model.Patient;
import com.sebas.service.IDetailConsultationService;
import com.sebas.service.IPatientService;

import javassist.NotFoundException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Controller
@RequestMapping(value = "/report/patient")
public class PatientReportController {

private static final String PATH = "src/main/resources/reportes";
	
	@Autowired
	private IDetailConsultationService service;
	
	@Autowired
	private IPatientService patientService;

	
	@GetMapping(value = "/{id}" )
	public void reporte01(HttpServletResponse response,
			@PathVariable(value = "id") Integer idPatient) throws JRException, IOException, NotFoundException {
		
		Patient patientSearch = patientService.find(idPatient);
		byte[] data = createPDF(patientSearch.getId(),patientSearch.getFullName());
		
		strearmReport(response, data, patientSearch.getFullName()+"_ClinicalHistory"+".pdf");
	}
	
	private byte[] createPDF(int idPatient, String namePatient) throws JRException {
		JasperReport report = JasperCompileManager.compileReport(PATH + "/reportePaciente.jrxml");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("titulo", namePatient);
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(service.getPatientHistory(idPatient));
		
		JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
		
		return JasperExportManager.exportReportToPdf(print);
	}
	
	private void strearmReport(HttpServletResponse response, byte[] data, String name) throws IOException {
	
			response.setContentType("application/x-download");
			response.setHeader("Content-disposition", "attachment; filename= " + name);
		
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		response.getOutputStream().flush();
	}
	
	@GetMapping
	public ModelAndView paginaReports() {
		ModelAndView model = new ModelAndView();
		model.addObject("patientList", patientService.findAll());
		model.setViewName("reports/reportPatient");
		return model;
	}
}
