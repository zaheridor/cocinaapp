package org.cocina.dto;

import java.util.Date;
import java.util.List;

import org.cocina.dao.jpa.Camarero;
import org.cocina.dao.jpa.Cliente;
import org.cocina.dao.jpa.Factura;
import org.cocina.dao.jpa.Mesa;

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
	private Date fechaFactura;
	private List<DetalleFacturaDTO> detalleFacturas;
	
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
	public Date getFechaFactura() {
		return fechaFactura;
	}
	
	public List<DetalleFacturaDTO> getDetalleFacturas() {
		return detalleFacturas;
	}

	public static class Builder {
		
		protected Factura factura = new Factura();
		private List<DetalleFacturaDTO> detalleFacturas;
		
		public Builder() {
			
		}
		
		public Builder(Integer id) {
			factura.setId(id);
		}
		
		public Builder cliente(Integer idCliente) {
			Cliente c = new Cliente();
			c.setId(idCliente);
			factura.setCliente(c);
			return this;
		}
		
		public Builder camarero(Integer idCamarero) {
			Camarero c = new Camarero();
			c.setId(idCamarero);
			factura.setCamarero(c);
			return this;
		}
		
		public Builder mesa(Integer idMesa) {
			Mesa m = new Mesa();
			m.setId(idMesa);
			factura.setMesa(m);
			return this;
		}
		
		public Builder fechaFactura(Date fechaFactura) {
			factura.setFechaFactura(fechaFactura);
			return this;
		}
		
		public Builder detalleFacturas(List<DetalleFacturaDTO> detalleFacturas) {
			this.detalleFacturas = detalleFacturas;
			return this;
		}
		
		public FacturaDTO build() {
			return new FacturaDTO(this);
		}
	}
	
	private FacturaDTO(Builder b) {
		this.id = b.factura.getId();
		this.idCliente = b.factura.getCliente().getId();
		this.idCamarero = b.factura.getCamarero().getId();
		this.idMesa = b.factura.getMesa().getId();
		this.fechaFactura = b.factura.getFechaFactura();
		this.detalleFacturas = b.detalleFacturas;
	}
}
