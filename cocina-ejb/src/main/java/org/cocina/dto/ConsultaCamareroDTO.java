package org.cocina.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ConsultaCamareroDTO {

	private Integer id;
	private String nombre;
	private String apellido;
	private String mes;
	private Date fechaFactura;
	private BigDecimal sumatoriaImporte;
	
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public BigDecimal getSumatoriaImporte() {
		return sumatoriaImporte;
	}
	public String getMes() {
		return mes;
	}
	
	public static class Builder {
		
		private Integer id;
		private String nombre;
		private String apellido;
		private String mes;
		private Date fechaFactura;
		private BigDecimal sumatoriaImporte;
		
		public Builder(Integer id) {
			this.id = id;
		}
		
		public Builder nombre(String nombre) {
			this.nombre = nombre;
			return this;
		}
		
		public Builder apellido(String apellido) {
			this.apellido = apellido;
			return this;
		}
		
		public Builder mes(String mes) {
			this.mes = mes;
			return this;
		}
		
		public Builder fechaFactura(Date fechaFactura) {
			this.fechaFactura = fechaFactura;
			return this;
		}
		
		public Builder sumatoriaImporte(BigDecimal sumatoriaImporte) {
			this.sumatoriaImporte = sumatoriaImporte;
			return this;
		}
		
		public ConsultaCamareroDTO build() {
			return new ConsultaCamareroDTO(this);
		}
	}
	
	private ConsultaCamareroDTO(Builder b) {
		this.id = b.id;
		this.nombre = b.nombre;
		this.apellido = b.apellido;
		this.mes = b.mes;
		this.fechaFactura = b.fechaFactura;
		this.sumatoriaImporte = b.sumatoriaImporte;
	}
}
