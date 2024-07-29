package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Factura")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double costoProcedimiento;

	@Column(length = 255, nullable = false)
	private String direccionEmisor;

	@Column(length = 255, nullable = false)
	private String direccionReceptor;

	private double honorariosBasico;

	@Column(length = 13, nullable = false)
	private String identificacionEmisor;

	@Column(length = 13, nullable = false)
	private String identificacionReceptor;

	@Column(length = 255, nullable = false)
	private String nombreEmisor;

	@Column(length = 255, nullable = false)
	private String nombreReceptor;

	@Column(length = 255, nullable = false)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(length = 50)
	private String formaPago;

	@ManyToOne
	@JoinColumn(name = "citaMedica_id")
	private CitaMedica citaMedica;

	public Factura() {
	}

	public Factura(int id, double costoProcedimiento, String direccionEmisor, String direccionReceptor,
			double honorariosBasico, String identificacionEmisor, String identificacionReceptor, String nombreEmisor,
			String nombreReceptor, String descripcion, Date fecha, String formaPago, CitaMedica citaMedica) {
		super();
		this.id = id;
		this.costoProcedimiento = costoProcedimiento;
		this.direccionEmisor = direccionEmisor;
		this.direccionReceptor = direccionReceptor;
		this.honorariosBasico = honorariosBasico;
		this.identificacionEmisor = identificacionEmisor;
		this.identificacionReceptor = identificacionReceptor;
		this.nombreEmisor = nombreEmisor;
		this.nombreReceptor = nombreReceptor;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.formaPago = formaPago;
		this.citaMedica = citaMedica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCostoProcedimiento() {
		return costoProcedimiento;
	}

	public void setCostoProcedimiento(double costoProcedimiento) {
		this.costoProcedimiento = costoProcedimiento;
	}

	public String getDireccionEmisor() {
		return direccionEmisor;
	}

	public void setDireccionEmisor(String direccionEmisor) {
		this.direccionEmisor = direccionEmisor;
	}

	public String getDireccionReceptor() {
		return direccionReceptor;
	}

	public void setDireccionReceptor(String direccionReceptor) {
		this.direccionReceptor = direccionReceptor;
	}

	public double getHonorariosBasico() {
		return honorariosBasico;
	}

	public void setHonorariosBasico(double honorariosBasico) {
		this.honorariosBasico = honorariosBasico;
	}

	public String getIdentificacionEmisor() {
		return identificacionEmisor;
	}

	public void setIdentificacionEmisor(String identificacionEmisor) {
		this.identificacionEmisor = identificacionEmisor;
	}

	public String getIdentificacionReceptor() {
		return identificacionReceptor;
	}

	public void setIdentificacionReceptor(String identificacionReceptor) {
		this.identificacionReceptor = identificacionReceptor;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}

}
