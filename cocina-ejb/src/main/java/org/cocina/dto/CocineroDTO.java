package org.cocina.dto;

import org.cocina.dao.jpa.Cocinero;

/**
 * Clase encargada de manejar la información de negocio de un cocinero.
 * @author zaheridor
 *
 */

public class CocineroDTO {

	private Integer id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public static class Builder {
		
		protected Cocinero cocinero = new Cocinero();
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			cocinero.setId(id);
		}
		
		public Builder nombre(String nombre) {
			cocinero.setNombre(nombre);
			return this;
		}
		
		public Builder primerApellido(String primerApellido) {
			cocinero.setPrimerApellido(primerApellido);
			return this;
		}
		
		public Builder segundoApellido(String segundoApellido) {
			cocinero.setSegundoApellido(segundoApellido);
			return this;
		}
		
		public CocineroDTO build() {
			return new CocineroDTO(this);
		}
	}
	
	private CocineroDTO(Builder b) {
		this.id = b.cocinero.getId();
		this.nombre = b.cocinero.getNombre();
		this.primerApellido = b.cocinero.getPrimerApellido();
		this.segundoApellido = b.cocinero.getSegundoApellido();
	}
}
