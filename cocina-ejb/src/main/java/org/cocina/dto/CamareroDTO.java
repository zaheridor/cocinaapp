package org.cocina.dto;

import org.cocina.dao.jpa.entity.Camarero;

/**
 * Clase encargada de manejar la información de negocio de un camarero.
 * @author zaheridor
 *
 */
public class CamareroDTO {

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

		protected Camarero camarero = new Camarero();
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			camarero.setId(id);
		}
		
		public Builder nombre(String nombre) {
			camarero.setNombre(nombre);
			return this;
		}
		
		public Builder primerApellido(String primerApellido) {
			camarero.setPrimerApellido(primerApellido);
			return this;
		}
		
		public Builder segundoApellido(String segundoApellido) {
			camarero.setSegundoApellido(segundoApellido);
			return this;
		}
		
		public CamareroDTO build() {
			return new CamareroDTO(this);
		}
	}
	
	private CamareroDTO(Builder b) {
		this.id = b.camarero.getId();
		this.nombre = b.camarero.getNombre();
		this.primerApellido = b.camarero.getPrimerApellido();
		this.segundoApellido = b.camarero.getSegundoApellido();
	}

}
