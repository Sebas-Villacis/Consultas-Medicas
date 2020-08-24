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

import com.sebas.model.Specialty;
import com.sebas.service.IDoctorService;
import com.sebas.service.ISpecialtyService;

import javassist.NotFoundException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/report/doctors")
public class DoctorReportController {
	

	private static final String PATH = "src/main/resources/reportes";
	
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecialtyService specialtyService;
	
	@GetMapping(value = "/specialty/{id}" )
	public void reporte01(HttpServletResponse response,
//			@RequestParam(value = "mode", defaultValue = "inline")
			@PathVariable(value = "id") Integer idSpecialty) throws JRException, IOException, NotFoundException {
		
		Specialty specialtySearch = specialtyService.find(idSpecialty);
		byte[] data = createPDF(idSpecialty,specialtySearch.getName() );
		
		strearmReport(response, data, specialtySearch.getName()+"_Doctors"+".pdf");
	}
	
	private byte[] createPDF(int idSpecialty, String nameSpecialty) throws JRException {
		JasperReport report = JasperCompileManager.compileReport(PATH + "/reporteDoctores.jrxml");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("titulo", nameSpecialty);
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(service.getDoctorsBySpecialty(idSpecialty));
		
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
	public ModelAndView paginaReport() {
		ModelAndView model = new ModelAndView();
		model.addObject("specialtyList", specialtyService.findAll());
		model.setViewName("reports/reportDoctor");
		return model;
	}
	
}
