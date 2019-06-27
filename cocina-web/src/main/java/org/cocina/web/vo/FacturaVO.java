package org.cocina.web.vo;

import java.util.Date;

public class FacturaVO {

	private Integer id;
	private Integer idCliente;
	private Integer idCamarero;
	private Integer idMesa;
	private Date fechaFactura;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdCamarero() {
		return idCamarero;
	}
	public void setIdCamarero(Integer idCamarero) {
		this.idCamarero = idCamarero;
	}
	public Integer getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	
}
