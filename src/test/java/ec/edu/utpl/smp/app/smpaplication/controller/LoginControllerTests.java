package ec.edu.utpl.smp.app.smpaplication.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;

@SpringBootTest
@WithMockUser(username = "admin", password = "12345", roles = { "USER", "ADMIN" })
public class LoginControllerTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void login() throws Exception {
		mvc.perform(get("/login").secure(true).contentType(MediaType.TEXT_HTML).sessionAttr("usuario", new Usuario()))
				.andExpect(redirectedUrl("/")).andDo(print());
	}
}
