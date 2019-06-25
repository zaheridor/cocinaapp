package org.cocina.dto;

import org.cocina.dao.jpa.Mesa;

/**
 * Clase encargada de manejar la información de negocio de una mesa atendida.
 * @author zaheridor
 *
 */
public class MesaDTO {

	private Integer id;
	private Integer numMaxComensales;
	private String ubicacion;
	
	public Integer getId() {
		return id;
	}
	public Integer getNumMaxComensales() {
		return numMaxComensales;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	
	public static class Builder {
		
		protected Mesa mesa = new Mesa();
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			mesa.setId(id);
		}
		
		public Builder numMaxComensales(Integer numMaxComensales) {
			mesa.setNumMaxComensales(numMaxComensales);
			return this;
		}
		
		public Builder ubicacion(String ubicacion) {
			mesa.setUbicacion(ubicacion);
			return this;
		}
		
		public MesaDTO build() {
			return new MesaDTO(this);
		}
	}
	
	private MesaDTO(Builder b) {
		this.id = b.mesa.getId();
		this.numMaxComensales = b.mesa.getNumMaxComensales();
		this.ubicacion = b.mesa.getUbicacion();
	}
}
