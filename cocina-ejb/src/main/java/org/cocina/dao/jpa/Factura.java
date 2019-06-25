package org.cocina.dao.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Clase base generada con Eclipse.
 * @author zaheridor
 *
 */
@Entity
@Table(name="FACTURA")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FACTURA_ID_GENERATOR", sequenceName="FACTURA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACTURA_ID_GENERATOR")
	@Column(name="ID")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FACTURA")
	private Date fechaFactura;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="factura")
	private List<DetalleFactura> detalleFacturas;
	
	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	//bi-directional many-to-one association to Camarero
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CAMARERO_ID")
	private Camarero camarero;
	
	//bi-directional many-to-one association to Mesa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MESA_ID")
	private Mesa mesa;
	
	public void addDetalleFactura(DetalleFactura detalle) {
		detalleFacturas.add(detalle);
		detalle.setFactura(this);
	}
	
	public void removeDetalleFactura(DetalleFactura detalle) {
		detalleFacturas.remove(detalle);
		detalle.setFactura(null);
	}

	public Factura() {
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Camarero getCamarero() {
		return camarero;
	}

	public void setCamarero(Camarero camarero) {
		this.camarero = camarero;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaFactura() {
		return this.fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camarero == null) ? 0 : camarero.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((detalleFacturas == null) ? 0 : detalleFacturas.hashCode());
		result = prime * result + ((fechaFactura == null) ? 0 : fechaFactura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mesa == null) ? 0 : mesa.hashCode());
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
		Factura other = (Factura) obj;
		if (camarero == null) {
			if (other.camarero != null)
				return false;
		} else if (!camarero.equals(other.camarero))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (detalleFacturas == null) {
			if (other.detalleFacturas != null)
				return false;
		} else if (!detalleFacturas.equals(other.detalleFacturas))
			return false;
		if (fechaFactura == null) {
			if (other.fechaFactura != null)
				return false;
		} else if (!fechaFactura.equals(other.fechaFactura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mesa == null) {
			if (other.mesa != null)
				return false;
		} else if (!mesa.equals(other.mesa))
			return false;
		return true;
	}

}