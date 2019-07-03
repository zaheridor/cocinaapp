package org.cocina.dao.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;


/**
 * Clase base generada con Eclipse.
 * @author zaheridor
 *
 */
@SqlResultSetMapping(name = "ResultadoFacturadoCamareroAlMes",
				  	 entities = {
				  			 @EntityResult(
						  			entityClass = org.cocina.dao.jpa.Camarero.class,
						  			fields = {
										@FieldResult(name="id", column="id_camarero"),
										@FieldResult(name="nombre", column="nombre"),
										@FieldResult(name="primer_apellido", column="apellido")
						  			}
				  			)
				  	},
				  	columns = @ColumnResult(name="sumatoriaImporte", type = BigDecimal.class)
)
@Entity
@Table(name="camarero")
@NamedQuery(name="Camarero.findAll", query="SELECT c FROM Camarero c")
public class Camarero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="camarero_id_generator", sequenceName="camarero_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="camarero_id_generator")
	@Column(name="id")
	private Integer id;

	@Column(name="nombre")
	private String nombre;

	@Column(name="primer_apellido")
	private String primerApellido;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="camarero")
	private List<Factura> facturas;

	public Camarero() {
	}
	
	public Camarero(Integer id, String nombre, String primerApellido) {
		this.id = id;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facturas == null) ? 0 : facturas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((primerApellido == null) ? 0 : primerApellido.hashCode());
		result = prime * result + ((segundoApellido == null) ? 0 : segundoApellido.hashCode());
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
		Camarero other = (Camarero) obj;
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (primerApellido == null) {
			if (other.primerApellido != null)
				return false;
		} else if (!primerApellido.equals(other.primerApellido))
			return false;
		if (segundoApellido == null) {
			if (other.segundoApellido != null)
				return false;
		} else if (!segundoApellido.equals(other.segundoApellido))
			return false;
		return true;
	}

}