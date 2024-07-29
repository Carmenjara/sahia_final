package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Medicamento")
public class Medicamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidad_Disponible;

	@Temporal(TemporalType.DATE)
	private Date fecha_Vencimiento;

	@Column(length = 255, nullable = false)
	private String nombre;

	@Column(length = 255)
	private String presentacion;

	@Column(length = 255)
	private String via_Administracion;

	public Medicamento() {
	}

	public Medicamento(int id, int cantidad_Disponible, Date fecha_Vencimiento, String nombre, String presentacion,
			String via_Administracion) {
		super();
		this.id = id;
		this.cantidad_Disponible = cantidad_Disponible;
		this.fecha_Vencimiento = fecha_Vencimiento;
		this.nombre = nombre;
		this.presentacion = presentacion;
		this.via_Administracion = via_Administracion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public int getCantidad_Disponible() {
		return cantidad_Disponible;
	}

	public void setCantidad_Disponible(int cantidad_Disponible) {
		this.cantidad_Disponible = cantidad_Disponible;
	}

	public Date getFecha_Vencimiento() {
		return fecha_Vencimiento;
	}

	public void setFecha_Vencimiento(Date fecha_Vencimiento) {
		this.fecha_Vencimiento = fecha_Vencimiento;
	}

	public String getVia_Administracion() {
		return via_Administracion;
	}

	public void setVia_Administracion(String via_Administracion) {
		this.via_Administracion = via_Administracion;
	}

}
