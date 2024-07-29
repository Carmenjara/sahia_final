package ec.edu.utpl.smp.app.smpaplication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		FlashMap flashMap = new FlashMap();

		flashMap.put("success",
				"Hola <strong>" + authentication.getName() + "</strong>, has iniciado sesión con éxito!");
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		System.out.println("Roles: " + getRoles(authentication));
		response.sendRedirect("/");

		if (authentication != null) {
			logger.info("El usuario '" + authentication.getName() + "' ha iniciado sesión!");

		}

		addSameSiteCookieAttribute(response);
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private Collection<? extends GrantedAuthority> getRoles(Authentication authentication) {
		return authentication.getAuthorities();
	}

	// Establecer cookie SameSite a Strict
	private void addSameSiteCookieAttribute(HttpServletResponse response) {
		Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		boolean firstHeader = true;
		for (String header : headers) { // there can be multiple Set-Cookie attributes
			if (firstHeader) {
				response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
				firstHeader = false;
				continue;
			}
			response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
		}
	}

}
