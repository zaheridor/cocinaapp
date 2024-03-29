package org.cocina.dao.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Entidad detalle de factura.
 * @author zaheridor
 *
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="detfactura_id_generator", sequenceName="detfactura_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="detfactura_id_generator")
	@Column(name="id")
	private Integer id;

	@Column(name="importe")
	private BigDecimal importe;

	@Column(name="plato")
	private String plato;

	//bi-directional many-to-one association to Factura
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="factura_id")
	private Factura factura;
	
	//bi-directional many-to-one association to Cocinero
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cocinero_id")
	private Cocinero cocinero;

	public Cocinero getCocinero() {
		return cocinero;
	}

	public void setCocinero(Cocinero cocinero) {
		this.cocinero = cocinero;
	}

	public DetalleFactura() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getPlato() {
		return this.plato;
	}

	public void setPlato(String plato) {
		this.plato = plato;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cocinero == null) ? 0 : cocinero.hashCode());
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + ((plato == null) ? 0 : plato.hashCode());
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
		DetalleFactura other = (DetalleFactura) obj;
		if (cocinero == null) {
			if (other.cocinero != null)
				return false;
		} else if (!cocinero.equals(other.cocinero))
			return false;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (plato == null) {
			if (other.plato != null)
				return false;
		} else if (!plato.equals(other.plato))
			return false;
		return true;
	}

}