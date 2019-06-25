package org.cocina.dto;

/**
 * Clase encargada de manejar la información de negocio de un camarero.
 * @author zaheridor
 *
 */
public class CamareroDTO {

	private Integer id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	
	public static class Builder {
		private Integer id;
		private String nombre;
		private String apellido1;
		private String apellido2;
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			this.id = id;
		}
		
		public Builder nombre(String nombre) {
			this.nombre = nombre;
			return this;
		}
		
		public Builder apellido1(String apellido1) {
			this.apellido1 = apellido1;
			return this;
		}
		
		public Builder apellido2(String apellido2) {
			this.apellido2 = apellido2;
			return this;
		}
		
		public CamareroDTO build() {
			return new CamareroDTO(this);
		}
	}
	
	private CamareroDTO(Builder b) {
		this.id = b.id;
		this.nombre = b.nombre;
		this.apellido1 = b.apellido1;
		this.apellido2 = b.apellido2;
	}

}
