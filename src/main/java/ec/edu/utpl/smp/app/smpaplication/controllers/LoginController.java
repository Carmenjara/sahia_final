package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		model.addAttribute("titulo", "Sistema de Automatización Hospitalaria Isidro Ayora");

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya inicio sesión anteriormente");
			return "redirect:/";
		}

		if (error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectos, inténtelo nuevamente!");
		}

		if (logout != null) {
			model.addAttribute("success", "La sesión se cerró correctamente!");
		}

		return "index";
	}

}
