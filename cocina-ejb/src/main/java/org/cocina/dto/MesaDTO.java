package org.cocina.dto;

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
		private Integer id;
		private Integer numMaxComensales;
		private String ubicacion;
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			this.id = id;
		}
		
		public Builder numMaxComensales(Integer numMaxComensales) {
			this.numMaxComensales = numMaxComensales;
			return this;
		}
		
		public Builder ubicacion(String ubicacion) {
			this.ubicacion = ubicacion;
			return this;
		}
		
		public MesaDTO build() {
			return new MesaDTO(this);
		}
	}
	
	private MesaDTO(Builder b) {
		this.id = b.id;
		this.numMaxComensales = b.numMaxComensales;
		this.ubicacion = b.ubicacion;
	}
}
