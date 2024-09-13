package ec.edu.utpl.smp.app.smpaplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;

@Controller
@RequestMapping("/pacientes")
@SessionAttributes("pacienteDTO")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;

	/* -------------- Gestión Paciente -------------- */

	/* -------------- Inicio Paciente -------------- */

	// @Secured("ROLE_USER")
	@RequestMapping("/inicio_pacientes")
	public String inicioPacientes(Model model) {
		model.addAttribute("titulo", "Gestión Pacientes");
		return "paciente/inicio_paciente";
	}

	/* -------------- Crear Paciente -------------- */

	// @Secured("ROLE_USER")
	@RequestMapping("/crear_paciente")
	public String crearPaciente(Model model) {
		DatosEncapsulados pacienteDTO = new DatosEncapsulados();
		Paciente paciente = new Paciente();
		pacienteDTO.setPaciente(paciente);

		/// List<Item> listRangos = itemService.listaItems(id);
		model.addAttribute("titulo", "Registrar Paciente");
		model.addAttribute("pacienteDTO", pacienteDTO);
		// model.addAttribute("items_rango", listRangos);

		return "paciente/crear_paciente";
	}

	/* -------------- Listar Paciente -------------- */

	@RequestMapping("/listar_pacientes")
	public String listarPacientes(Model model) {
		model.addAttribute("titulo", "Pacientes Registrados");
		model.addAttribute("listPacientes", pacienteService.getAllPersonas());

		return "paciente/listar_paciente";
	}

	/* -------------- Guardar Paciente -------------- */

	// @Secured("ROLE_USER")
	@RequestMapping(value = "/guardar_paciente", method = RequestMethod.POST)
	public String guardarPaciente(@ModelAttribute("personal") DatosEncapsulados pacienteDTO, RedirectAttributes flash,
			SessionStatus status) {

		Paciente persona = pacienteDTO.getPaciente();
		persona.setEdad(0);

		/*
		 * String mensajeFlash = (pacienteDTO.getIdpersonal() != null) ?
		 * "Personal Editado Exitosamente!" : "Personal Registrado Exitosamente!";
		 * 
		 * personalService.save(pacienteDTO);
		 */
		status.setComplete();
		// flash.addFlashAttribute("success", mensajeFlash);
		System.out.println("Datos Guardados");
		return "redirect:/personal/listar_personal";
	}

}
