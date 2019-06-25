package org.cocina.dto;

import java.math.BigDecimal;

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
		private Integer id;
		private Integer idFactura;
		private Integer idCocinero;
		private String plato;
		private BigDecimal importe;
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			this.id = id;
		}
		
		public Builder factura(Integer idFactura) {
			this.idFactura = idFactura;
			return this;
		}
		
		public Builder cocinero(Integer idCocinero) {
			this.idCocinero = idCocinero;
			return this;
		}
		
		public Builder plato(String plato) {
			this.plato = plato;
			return this;
		}
		
		public Builder importe(BigDecimal importe) {
			this.importe = importe;
			return this;
		}
		
		public DetalleFacturaDTO build() {
			return new DetalleFacturaDTO(this);
		}
	}
	
	private DetalleFacturaDTO(Builder b) {
		this.id = b.id;
		this.idFactura = b.idFactura;
		this.idCocinero = b.idCocinero;
		this.plato = b.plato;
		this.importe = b.importe;
	}
}
