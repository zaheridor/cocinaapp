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
@Table(name="MESA")
@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
public class Mesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MESA_ID_GENERATOR", sequenceName="MESA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESA_ID_GENERATOR")
	@Column(name="ID")
	private Integer id;

	@Column(name="NUM_MAX_COMENSALES")
	private Integer numMaxComensales;

	@Column(name="UBICACION")
	private String ubicacion;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="mesa")
	private List<Factura> facturas;

	public Mesa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumMaxComensales() {
		return this.numMaxComensales;
	}

	public void setNumMaxComensales(Integer numMaxComensales) {
		this.numMaxComensales = numMaxComensales;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facturas == null) ? 0 : facturas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numMaxComensales == null) ? 0 : numMaxComensales.hashCode());
		result = prime * result + ((ubicacion == null) ? 0 : ubicacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		if (facturas == null) {
			if (other.facturas != null)
				return false;
		} else if (!facturas.equals(other.facturas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numMaxComensales == null) {
			if (other.numMaxComensales != null)
				return false;
		} else if (!numMaxComensales.equals(other.numMaxComensales))
			return false;
		if (ubicacion == null) {
			if (other.ubicacion != null)
				return false;
		} else if (!ubicacion.equals(other.ubicacion))
			return false;
		return true;
	}

}