package ec.edu.utpl.smp.app.smpaplication.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IRolesRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Roles;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.RolesServiceImpl;

@SpringBootTest
public class RolesServiceImplTests {

	@Mock
	private IRolesRepository repositoryMock;

	@InjectMocks
	private RolesServiceImpl serviceRolesMock;

	private Roles objRoles = new Roles();

	private List<Roles> listRoles = new ArrayList<>();

	@Test
	public void save() {
		objetos();
		// serviceRolesMock.save(objRoles);
		verify(repositoryMock, times(1)).save(objRoles);

		ArgumentCaptor<Roles> captor = ArgumentCaptor.forClass(Roles.class);
		verify(repositoryMock).save(captor.capture());
		// assertTrue(captor.getValue().getRol().equals(objRoles.getRol()));

	}

	public void objetos() {
		Usuario objUser = new Usuario();
		// objRoles.setRoles_id(Long.valueOf(1));
		// objRoles.setRol("ADMIN");
		// objRoles.setUsuarios_id(objUser.getUsuarios_id().intValue());

		listRoles.add(objRoles);
	}
}
