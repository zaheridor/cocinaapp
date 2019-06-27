package org.cocina.dto;

import java.util.ArrayList;
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
		private List<DetalleFacturaDTO> detalleFacturas = new ArrayList<>();
		
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
		
		public Builder adicionarDetalle(DetalleFacturaDTO detalleFactura) {
			this.detalleFacturas.add(detalleFactura);
			return this;
		}
		
		public FacturaDTO build() {
			return new FacturaDTO(this);
		}
	}
	
	private FacturaDTO(Builder b) {
		if(b.factura.getCliente() != null) {
			this.idCliente = b.factura.getCliente().getId();
		}
		
		if(b.factura.getCamarero() != null) {
			this.idCamarero = b.factura.getCamarero().getId();
		}
		
		if(b.factura.getMesa() != null) {
			this.idMesa = b.factura.getMesa().getId();
		}
		
		this.id = b.factura.getId();
		this.fechaFactura = b.factura.getFechaFactura();
		this.detalleFacturas = b.detalleFacturas;
	}
}
