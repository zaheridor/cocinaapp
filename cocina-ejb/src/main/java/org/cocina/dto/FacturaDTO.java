package org.cocina.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase encargada de manejar la información de negocio de factura.
 * @author zaheridor
 *
 */
public class FacturaDTO {

	private Integer id;
	private Integer idCliente;
	private Integer idCamarero;
	private Integer idMesa;
	private LocalDate fechaFactura;
	private List<DetalleFacturaDTO> detalleDTO;
	
	public Integer getId() {
		return id;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public Integer getIdCamarero() {
		return idCamarero;
	}
	public Integer getIdMesa() {
		return idMesa;
	}
	public LocalDate getFechaFactura() {
		return fechaFactura;
	}
	
	public List<DetalleFacturaDTO> getDetalleDTO() {
		return detalleDTO;
	}
	
	public static class Builder {
		private Integer id;
		private Integer idCliente;
		private Integer idCamarero;
		private Integer idMesa;
		private LocalDate fechaFactura;
		private List<DetalleFacturaDTO> detalleDTO;
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			this.id = id;
		}
		
		public Builder cliente(Integer idCliente) {
			this.idCliente = idCliente;
			return this;
		}
		
		public Builder camarero(Integer idCamarero) {
			this.idCamarero = idCamarero;
			return this;
		}
		
		public Builder mesa(Integer idMesa) {
			this.idMesa = idMesa;
			return this;
		}
		
		public Builder fechaFactura(LocalDate fechaFactura) {
			this.fechaFactura = fechaFactura;
			return this;
		}
		
		public Builder detalleFactura(List<DetalleFacturaDTO> detalleDTO) {
			this.detalleDTO = detalleDTO;
			return this;
		}
		
		public FacturaDTO build() {
			return new FacturaDTO(this);
		}
	}
	
	private FacturaDTO(Builder b) {
		this.id = b.id;
		this.idCliente = b.idCliente;
		this.idCamarero = b.idCamarero;
		this.idMesa = b.idMesa;
		this.fechaFactura = b.fechaFactura;
		this.detalleDTO = b.detalleDTO;
	}
}
