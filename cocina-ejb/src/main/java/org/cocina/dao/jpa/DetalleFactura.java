package org.cocina.dao.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Clase base generada con Eclipse.
 * @author zaheridor
 *
 */
@Entity
@Table(name="DETALLE_FACTURA")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETALLE_FACTURA_ID_GENERATOR", sequenceName="DETALLE_FACTURA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALLE_FACTURA_ID_GENERATOR")
	@Column(name="ID")
	private Integer id;

	@Column(name="IMPORTE")
	private BigDecimal importe;

	@Column(name="PLATO")
	private String plato;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="FACTURA_ID")
	private Factura factura;
	
	//bi-directional many-to-one association to Cocinero
	@ManyToOne
	@JoinColumn(name="COCINERO_ID")
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

}