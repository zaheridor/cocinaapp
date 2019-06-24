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
@Table(name="CAMARERO")
@NamedQuery(name="Camarero.findAll", query="SELECT c FROM Camarero c")
public class Camarero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CAMARERO_ID_GENERATOR", sequenceName="CAMARERO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAMARERO_ID_GENERATOR")
	@Column(name="ID")
	private Integer id;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;

	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="camarero")
	private List<Factura> facturas;

	public Camarero() {
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

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

}