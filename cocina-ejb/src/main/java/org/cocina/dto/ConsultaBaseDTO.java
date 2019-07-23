package org.cocina.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Objeto base para almacenar información de las consultas entre varios dominios de datos.
 */
public class ConsultaBaseDTO {

	private Integer id;
	private String nombre;
	private String apellido;
	private String mes;
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
		
		public Builder sumatoriaImporte(BigDecimal sumatoriaImporte) {
			this.sumatoriaImporte = sumatoriaImporte;
			return this;
		}
		
		public ConsultaBaseDTO build() {
			return new ConsultaBaseDTO(this);
		}
	}
	
	private ConsultaBaseDTO(Builder b) {
		this.id = b.id;
		this.nombre = b.nombre;
		this.apellido = b.apellido;
		this.mes = b.mes;
		this.sumatoriaImporte = b.sumatoriaImporte;
	}
}
