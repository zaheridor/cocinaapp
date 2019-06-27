package org.cocina.web.vo;

import java.math.BigDecimal;

public class DetalleFacturaVO {

	private Integer id;
	private Integer idFactura;
	private Integer idCocinero;
	private String plato;
	private BigDecimal importe;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public Integer getIdCocinero() {
		return idCocinero;
	}
	public void setIdCocinero(Integer idCocinero) {
		this.idCocinero = idCocinero;
	}
	public String getPlato() {
		return plato;
	}
	public void setPlato(String plato) {
		this.plato = plato;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
}
