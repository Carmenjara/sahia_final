package ec.edu.utpl.smp.app.smpaplication.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 13, nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "La identificación solo puede contener letras y números.")
	private String identificacion;

	@Column(length = 255, nullable = false)
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+$", message = "Los apellidos solo permiten letras y espacios, incluida la ñ.")
	private String apellidos;

	@Column(length = 30, nullable = false)
	private String ciudad_residencia;

	@Column(length = 255, nullable = false)
	private String direccion;

	@Column(length = 255)
	private String correo;

	// @Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha_nacimiento;

	@Column(length = 255, nullable = false)
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+$", message = "Los nombres solo permite letras y espacios, incluida la ñ.")
	private String nombres;

	@Column(length = 10, nullable = false)
	private String genero;

	private String telefono;

	public Persona() {
	}

	public Persona(int id, String identificacion, String apellidos, String ciudad_residencia, String direccion,
			String correo, LocalDate fecha_nacimiento, String nombres, String genero, String telefono) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.apellidos = apellidos;
		this.ciudad_residencia = ciudad_residencia;
		this.direccion = direccion;
		this.correo = correo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nombres = nombres;
		this.genero = genero;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion.toUpperCase();
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos.toUpperCase();
	}

	public String getCiudad_residencia() {
		return ciudad_residencia;
	}

	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudad_residencia = ciudad_residencia.toUpperCase();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion.toUpperCase();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo.toLowerCase();
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres.toUpperCase();
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
