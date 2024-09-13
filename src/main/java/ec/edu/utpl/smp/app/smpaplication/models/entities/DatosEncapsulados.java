package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class DatosEncapsulados {
	private Persona persona;
	private Usuario usuario;
	private Roles rol;
	private List<UsuarioRol> usuarioRoles;
	private UsuarioRol userRol;
	private UsuarioRolId rolId;
	private Paciente paciente;
	private Horario horario;
	private Medico medico;
	private MedicoHorario medicoHorario;
	private ConsultorioMedico consultorioMedico;
	private Consultorios consultorio;
	private Especialidad especialidad;
	private MedicoEspecialidad medicoEspecialidad;
	private HistorialMedico historial;
	private Receta receta;
	private Medicamento medicamento;
	private MedicamentoReceta medicamentoReceta;
	private MedicamentoRecetaId medicamentoRecetaId;
	private Factura factura;
	private Long citaId;
	private List<Integer> especialidadesSeleccionadas;
	private List<Long> medicamentoIds;
	private List<MedicoEspecialidad> medicoEspecialidades;

	private CitaMedica cita;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	public DatosEncapsulados() {
	}

	public DatosEncapsulados(Persona persona, Usuario usuario, Roles rol, List<UsuarioRol> usuarioRoles,
			UsuarioRol userRol, UsuarioRolId rolId, Paciente paciente, Horario horario, Medico medico,
			MedicoHorario medicoHorario, ConsultorioMedico consultorioMedico, Consultorios consultorio,
			Especialidad especialidad, MedicoEspecialidad medicoEspecialidad, HistorialMedico historial, Receta receta,
			Medicamento medicamento, MedicamentoReceta medicamentoReceta, MedicamentoRecetaId medicamentoRecetaId,
			Factura factura, Long citaId, List<Integer> especialidadesSeleccionadas, List<Long> medicamentoIds,
			List<MedicoEspecialidad> medicoEspecialidades, CitaMedica cita, LocalDate fechaNacimiento) {
		super();
		this.persona = persona;
		this.usuario = usuario;
		this.rol = rol;
		this.usuarioRoles = usuarioRoles;
		this.userRol = userRol;
		this.rolId = rolId;
		this.paciente = paciente;
		this.horario = horario;
		this.medico = medico;
		this.medicoHorario = medicoHorario;
		this.consultorioMedico = consultorioMedico;
		this.consultorio = consultorio;
		this.especialidad = especialidad;
		this.medicoEspecialidad = medicoEspecialidad;
		this.historial = historial;
		this.receta = receta;
		this.medicamento = medicamento;
		this.medicamentoReceta = medicamentoReceta;
		this.medicamentoRecetaId = medicamentoRecetaId;
		this.factura = factura;
		this.citaId = citaId;
		this.especialidadesSeleccionadas = especialidadesSeleccionadas;
		this.medicamentoIds = medicamentoIds;
		this.medicoEspecialidades = medicoEspecialidades;
		this.cita = cita;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public UsuarioRol getUserRol() {
		return userRol;
	}

	public void setUserRol(UsuarioRol userRol) {
		this.userRol = userRol;
	}

	public UsuarioRolId getRolId() {
		return rolId;
	}

	public void setRolId(UsuarioRolId rolId) {
		this.rolId = rolId;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public CitaMedica getCita() {
		return cita;
	}

	public void setCita(CitaMedica cita) {
		this.cita = cita;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public MedicoHorario getMedicoHorario() {
		return medicoHorario;
	}

	public void setMedicoHorario(MedicoHorario medicoHorario) {
		this.medicoHorario = medicoHorario;
	}

	public ConsultorioMedico getConsultorioMedico() {
		return consultorioMedico;
	}

	public void setConsultorioMedico(ConsultorioMedico consultorioMedico) {
		this.consultorioMedico = consultorioMedico;
	}

	public Consultorios getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorios consultorio) {
		this.consultorio = consultorio;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public MedicoEspecialidad getMedicoEspecialidad() {
		return medicoEspecialidad;
	}

	public void setMedicoEspecialidad(MedicoEspecialidad medicoEspecialidad) {
		this.medicoEspecialidad = medicoEspecialidad;
	}

	public List<UsuarioRol> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	public HistorialMedico getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialMedico historial) {
		this.historial = historial;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public MedicamentoReceta getMedicamentoReceta() {
		return medicamentoReceta;
	}

	public void setMedicamentoReceta(MedicamentoReceta medicamentoReceta) {
		this.medicamentoReceta = medicamentoReceta;
	}

	public MedicamentoRecetaId getMedicamentoRecetaId() {
		return medicamentoRecetaId;
	}

	public void setMedicamentoRecetaId(MedicamentoRecetaId medicamentoRecetaId) {
		this.medicamentoRecetaId = medicamentoRecetaId;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Long getCitaId() {
		return citaId;
	}

	public void setCitaId(Long citaId) {
		this.citaId = citaId;
	}

	public List<Integer> getEspecialidadesSeleccionadas() {
		return especialidadesSeleccionadas;
	}

	public void setEspecialidadesSeleccionadas(List<Integer> especialidadesSeleccionadas) {
		this.especialidadesSeleccionadas = especialidadesSeleccionadas;
	}

	public List<Long> getMedicamentoIds() {
		return medicamentoIds;
	}

	public void setMedicamentoIds(List<Long> medicamentoIds) {
		this.medicamentoIds = medicamentoIds;
	}

	public List<MedicoEspecialidad> getMedicoEspecialidades() {
		return medicoEspecialidades;
	}

	public void setMedicoEspecialidades(List<MedicoEspecialidad> medicoEspecialidades) {
		this.medicoEspecialidades = medicoEspecialidades;
	}

}