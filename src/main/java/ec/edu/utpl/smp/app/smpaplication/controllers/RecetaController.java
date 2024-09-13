package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Receta;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IRecetaService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Controller
@RequestMapping("/receta")
@SessionAttributes("receta")
public class RecetaController {

	@Autowired
	private IRecetaService recetaService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPacienteService pacienteService;

	/* -------------- Todas las recetas -------------- */

	@RequestMapping("/listar_recetas")
	public String verRecetasMedicoAdmin(Model model) {

		List<Receta> recetas = recetaService.getAllRecetas();

		model.addAttribute("recetas", recetas);
		model.addAttribute("titulo", "Recetas de Pacientes");
		return "receta/listar_recetas";
	}

	/* -------------- Recetas por Paciente logueado-------------- */

	@RequestMapping("/listar_recetas_paciente")
	public String verRecetasPaciente(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);
		// Se obtiene paciente desde el usuario logueado
		Paciente paciente = pacienteService.getPacienteByUsuario(usuario.getId());

		List<Receta> recetas = recetaService.getAllRecetaPorPaciente(paciente.getId());

		model.addAttribute("recetas", recetas);
		model.addAttribute("titulo", "Mis Recetas");
		return "receta/listar_recetas_paciente";
	}

}
