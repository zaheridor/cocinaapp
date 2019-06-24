package org.cocina.dao.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * Clase base generada con Eclipse.
 * @author zaheridor
 *
 */
@Entity
@Table(name="COCINERO")
@NamedQuery(name="Cocinero.findAll", query="SELECT c FROM Cocinero c")
public class Cocinero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COCINERO_ID_GENERATOR", sequenceName="COCINERO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COCINERO_ID_GENERATOR")
	@Column(name="ID")
	private Integer id;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;

	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="cocinero")
	private List<DetalleFactura> detalleFacturas;

	public Cocinero() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

}