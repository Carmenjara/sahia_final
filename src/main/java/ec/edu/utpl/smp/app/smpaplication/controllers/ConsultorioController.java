package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Consultorios;
import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IConsultorioMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IConsultoriosService;

@Controller
@RequestMapping("/consultorio")
@SessionAttributes("consultorio")
public class ConsultorioController {

	@Autowired
	private IConsultoriosService consultorioService;

	@Autowired
	private IConsultorioMedicoService consultorioMedicoService;

	/* -------------- Gestión Consultorios -------------- */

	/* -------------- Inicio Consultorios -------------- */

	@RequestMapping("/inicio_consultorio")
	public String verPaginaC(Model model) {
		model.addAttribute("titulo", "Gestión de Consultorios");
		return "consultorio/inicio_consultorio";
	}

	/* -------------- Listar Consultorios -------------- */

	@RequestMapping("/listar_consultorio")
	public String verPaginaP(Model model) {

		List<DatosEncapsulados> consultoriosAsignados = consultorioMedicoService.findConsultoriosWithMedico();
		System.out.println("Total de consultorios: " + consultoriosAsignados.size());

		model.addAttribute("consultoriosAsignados", consultoriosAsignados);
		model.addAttribute("titulo", "Consultorios Registrados");
		return "consultorio/listar_consultorio";
	}

	/* -------------- Inhabilitar Consultorio -------------- */
	@RequestMapping("/inhabilitar_consultorio/{id}")
	public String inhabilitarConsultorio(@PathVariable(name = "id") int id, RedirectAttributes flash,
			SessionStatus status) {

		Consultorios consultorio = consultorioService.findByIdConsultorio(id);

		// Solo se inhabilitan consultorios que no estan siendo usados
		if (consultorio.getEstado() == 0) {
			flash.addFlashAttribute("error", "Consultorio no se puede inhabilitar, se encuentra asociado a Médico.");
			return "redirect:/consultorio/listar_consultorio";
		} else if (consultorio.getEstado() == 2) {
			flash.addFlashAttribute("error", "Consultorio ya se encuentra inhabilitado.");
			return "redirect:/consultorio/listar_consultorio";
		} else {
			// Establecer estado en 2 como Inhabilitado
			System.out.println("Se debe guardar en estado 2 ");
			consultorio.setEstado(2);
			consultorioService.save(consultorio);
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se inhabilitó el Consultorio de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/consultorio/listar_consultorio";
	}

	/* -------------- Editar Consultorio -------------- */

	@RequestMapping("/editar_consultorio/{id}")
	public String mostrarEditarConsultorio(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Consultorios consultorio = consultorioService.findByIdConsultorio(id);
		if (consultorio == null) {
			flash.addFlashAttribute("error", "No se encontró Consultorio.");
			return "redirect:/consultorio/listar_consultorio";
		} else {
			DatosEncapsulados consultorioDTO = new DatosEncapsulados();
			consultorioDTO.setConsultorio(consultorio);

			model.addAttribute("consultorioDTO", consultorioDTO);

			return "consultorio/editar_consultorio";
		}
	}

	@RequestMapping(value = "/guardar_consultorio_editado/{id}", method = RequestMethod.POST)
	public String guardarConsultorioEditado(@PathVariable("id") int id,
			@ModelAttribute("citaDTO") DatosEncapsulados consultorioDTO, RedirectAttributes flash, SessionStatus status,
			Model model) {

		Consultorios consultorio = consultorioDTO.getConsultorio();
		Consultorios consultorioAnterior = consultorioService.findByIdConsultorio(consultorio.getId());
		if (consultorio == null) {
			flash.addFlashAttribute("error", "Error al guardar Consultorio.");
			return "redirect:/consultorio/listar_consultorio";
		} else if (consultorioAnterior.getEstado() == 0 && consultorio.getEstado() != 0) {

			DatosEncapsulados consultorioDTONew = new DatosEncapsulados();
			consultorioDTONew.setConsultorio(consultorio);
			model.addAttribute("consultorioDTO", consultorioDTONew);
			model.addAttribute("error", "Consultorio no se puede inhabilitar, se encuentra asociado a Médico.");

			return "consultorio/editar_consultorio";

		} else if ((consultorioAnterior.getEstado() == 2 || consultorioAnterior.getEstado() == 1)
				&& consultorio.getEstado() == 0) {

			DatosEncapsulados consultorioDTONew = new DatosEncapsulados();
			consultorioDTONew.setConsultorio(consultorio);
			model.addAttribute("consultorioDTO", consultorioDTONew);
			model.addAttribute("error",
					"Consultorio no se puede indisponer directamente, se debe asociar a un Médico.");

			return "consultorio/editar_consultorio";

		} else {

			consultorioService.save(consultorio);
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se editó el Consultorio de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/consultorio/listar_consultorio";
	}

	/* -------------- Crear Consultorio-------------- */

	@RequestMapping("/crear_consultorio")
	public String crearUsuario(Model model) {
		DatosEncapsulados consultorioDTO = new DatosEncapsulados();
		Consultorios consultorio = new Consultorios();
		consultorioDTO.setConsultorio(consultorio);

		model.addAttribute("titulo", "Registrar Consultorio");
		model.addAttribute("consultorioDTO", consultorioDTO);

		return "consultorio/crear_consultorio";
	}
	/* -------------- Guardar Consultorio -------------- */

	@RequestMapping(value = "/crear_consultorio", method = RequestMethod.POST)
	public String guardarCitaMedica(@ModelAttribute("consultorioDTO") DatosEncapsulados consultorioDTO,
			RedirectAttributes flash, SessionStatus status) {

		Consultorios consultorio = consultorioDTO.getConsultorio();
		// Establecer estado de consultorio en 1, disponible

		if (consultorio == null) {
			flash.addFlashAttribute("error", "Error al crear Consultorio.");
			return "redirect:/consultorio/listar_consultorio";
		} else {
			consultorio.setEstado(1);
			consultorioService.save(consultorio);
			status.setComplete();
			flash.addFlashAttribute("success", "Consultorio creado exitosamente.");
		}

		System.out.println("Datos Guardados");
		return "redirect:/consultorio/listar_consultorio";
	}

}
