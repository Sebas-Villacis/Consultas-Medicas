package com.sebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultasApplication {
/***
 * Suposiciones:
 * 1.- Una especialidad no se elimina solo se modifica o agrega
 * 2.- Al eliminar un doctor se elimina todos sus hijos(tabla MedicalConsultation & DetialCoinsultation) solo por cuestion de trabajo final mas no como trabajo profesional
 * 3.- Al eliminar un paciente se elimina todos sus hijos(tabla MedicalConsultation & DetialCoinsultation) solo por cuestion de trabajo final mas no como trabajo profesional
 * 4.- Al eliminar un registro de la tabla de MedicalConsultation tambien se elimina de la tabla DetialConsultation.
 *
 */
	public static void main(String[] args) {
		SpringApplication.run(ConsultasApplication.class, args);
	}

}
