package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ec.edu.utpl.smp.app.smpaplication.models.entities.HistorialMedico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IHistoriaMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Controller
@RequestMapping("/historialmedico")
@SessionAttributes("historialmedico")
public class HistorialMedicoController {

	@Autowired
	private IHistoriaMedicoService historialMedicoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPacienteService pacienteService;

	/* -------------- Todo el Historial -------------- */

	@RequestMapping("/listar_todo_historial")
	public String verHistorialMedicoAdmin(Model model) {

		List<HistorialMedico> historialMedico = historialMedicoService.getAllHistorialMedico();

		model.addAttribute("historialMedico", historialMedico);
		model.addAttribute("titulo", "Historial Médico de Pacientes");
		return "historialmedico/listar_todo_historialmedico";
	}

	/* -------------- Historial por Paciente logueado-------------- */

	@RequestMapping("/listar_historial_paciente")
	public String verHistorialMedicoPaciente(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);
		// Se obtiene paciente desde el usuario logueado
		Paciente paciente = pacienteService.getPacienteByUsuario(usuario.getId());

		List<HistorialMedico> historialMedico = historialMedicoService
				.getAllHistorialMedicoPorPaciente(paciente.getId());

		model.addAttribute("historialMedico", historialMedico);
		model.addAttribute("titulo", "Historial Médico de Paciente");
		return "historialmedico/listar_historialmedico_paciente";
	}

}
