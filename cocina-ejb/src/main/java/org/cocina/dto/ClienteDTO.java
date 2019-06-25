package org.cocina.dto;

import org.cocina.dao.jpa.Cliente;

/**
 * Clase encargada de manejar la información de negocio de un cliente.
 * @author zaheridor
 *
 */

public class ClienteDTO {

	private Integer id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String observaciones;

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
	public String getObservaciones() {
		return observaciones;
	}
	
	public static class Builder {
		
		protected Cliente cliente = new Cliente();
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			cliente.setId(id);
		}
		
		public Builder nombre(String nombre) {
			cliente.setNombre(nombre);
			return this;
		}
		
		public Builder primerApellido(String primerApellido) {
			cliente.setPrimerApellido(primerApellido);
			return this;
		}
		
		public Builder segundoApellido(String segundoApellido) {
			cliente.setSegundoApellido(segundoApellido);
			return this;
		}
		
		public Builder observaciones(String observaciones) {
			cliente.setObservaciones(observaciones);
			return this;
		}
		
		public ClienteDTO build() {
			return new ClienteDTO(this);
		}
		
	}
	
	private ClienteDTO(Builder b) {
		this.id = b.cliente.getId();
		this.nombre = b.cliente.getNombre();
		this.primerApellido = b.cliente.getPrimerApellido();
		this.segundoApellido = b.cliente.getSegundoApellido();
		this.observaciones = b.cliente.getObservaciones();
	}
}
