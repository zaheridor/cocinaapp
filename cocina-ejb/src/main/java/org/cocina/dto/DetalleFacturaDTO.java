package org.cocina.dto;

import java.math.BigDecimal;

import org.cocina.dao.jpa.entity.Cocinero;
import org.cocina.dao.jpa.entity.DetalleFactura;
import org.cocina.dao.jpa.entity.Factura;

/**
 * Clase encargada de manejar la información de negocio del detalle de factura.
 * @author zaheridor
 *
 */

public class DetalleFacturaDTO {

	private Integer id;
	private Integer idFactura;
	private Integer idCocinero;
	private String plato;
	private BigDecimal importe;
	
	public Integer getId() {
		return id;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public Integer getIdCocinero() {
		return idCocinero;
	}
	public String getPlato() {
		return plato;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	
	public static class Builder {
		
		protected DetalleFactura detalle = new DetalleFactura();
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			detalle.setId(id);
		}
		
		public Builder factura(Integer idFactura) {
			Factura f = new Factura();
			f.setId(idFactura);
			detalle.setFactura(f);
			return this;
		}
		
		public Builder cocinero(Integer idCocinero) {
			Cocinero c = new Cocinero();
			c.setId(idCocinero);
			detalle.setCocinero(c);
			return this;
		}
		
		public Builder plato(String plato) {
			detalle.setPlato(plato);
			return this;
		}
		
		public Builder importe(BigDecimal importe) {
			detalle.setImporte(importe);
			return this;
		}
		
		public DetalleFacturaDTO build() {
			return new DetalleFacturaDTO(this);
		}
	}
	
	private DetalleFacturaDTO(Builder b) {
		if(b.detalle.getFactura() != null) {
			this.idFactura = b.detalle.getFactura().getId();
		}
		
		if(b.detalle.getCocinero() != null) {
			this.idCocinero = b.detalle.getCocinero().getId();
		}

		this.id = b.detalle.getId();
		this.plato = b.detalle.getPlato();
		this.importe = b.detalle.getImporte();
	}
}
