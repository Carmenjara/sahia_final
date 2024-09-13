package ec.edu.utpl.smp.app.smpaplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IUsuarioRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.UsuarioServiceImpl;

@SpringBootTest
public class UsuarioServiceImplTests {

	@Mock
	private IUsuarioRepository repositoryMock;

	@InjectMocks
	private UsuarioServiceImpl serviceUserMock;

	// private Usuario objUser = new Usuario();

	private List<Usuario> listUsuarios = new ArrayList<>();

	@Test
	public void getAllUsers() {
		// objetos();
		Mockito.when(repositoryMock.findAll()).thenReturn(listUsuarios);
		Iterable<Usuario> listUserExpected = serviceUserMock.getAllUsers();
		assertEquals(listUserExpected, listUsuarios);
		verify(repositoryMock).findAll();
	}
	/*
	 * @Test public void save() { objetos(); serviceUserMock.save(objUser);
	 * verify(repositoryMock, times(1)).save(objUser); ArgumentCaptor<Usuario>
	 * captor = ArgumentCaptor.forClass(Usuario.class);
	 * verify(repositoryMock).save(captor.capture());
	 * assertTrue(captor.getValue().getNombres().equals(objUser.getNombres())); }
	 * 
	 * @Test public void get() { objetos();
	 * Mockito.when(repositoryMock.findById(objUser.getUsuarios_id())).thenReturn(
	 * Optional.of(objUser)); Usuario usuarioExpected =
	 * serviceUserMock.get(objUser.getUsuarios_id());
	 * assertThat(usuarioExpected).isSameAs(objUser);
	 * verify(repositoryMock).findById(objUser.getUsuarios_id());
	 * 
	 * }
	 * 
	 * @Test public void delete() { objetos();
	 * Mockito.when(repositoryMock.findById(objUser.getUsuarios_id())).thenReturn(
	 * Optional.of(objUser)); serviceUserMock.delete(objUser.getUsuarios_id());
	 * verify(repositoryMock).deleteById(objUser.getUsuarios_id()); }
	 * 
	 * public void objetos() {
	 * 
	 * objUser.setUsuarios_id(Long.valueOf(1)); objUser.setCedula("1102734152");
	 * objUser.setNombres("Juan"); objUser.setApellidos("Condolo");
	 * objUser.setUsername("jcondolo"); objUser.setPassword("12345");
	 * 
	 * listUsuarios.add(objUser); }
	 */
}
