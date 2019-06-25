package org.cocina.ejb;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.cocina.dao.jpa.Camarero;
import org.cocina.dao.jpa.Cliente;
import org.cocina.dao.jpa.Cocinero;
import org.cocina.dao.jpa.DetalleFactura;
import org.cocina.dao.jpa.Factura;
import org.cocina.dao.jpa.Mesa;
import org.cocina.dto.DetalleFacturaDTO;
import org.cocina.dto.FacturaDTO;

/**
 * Session Bean implementation class CocinaEJB
 */
@Stateless
public class CocinaEJB implements CocinaEJBRemote, CocinaEJBLocal {
	
	@PersistenceContext(unitName = "cocinaPU")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public CocinaEJB() {
    }

    //TODO: borrar método de prueba.
	@Override
	public void test() {
		Mesa m = new Mesa();
		m.setNumMaxComensales(10);
		m.setUbicacion("Ventana");
		em.persist(m);
	}

	@Override
	public boolean guardarFactura(FacturaDTO facturaDTO) {
		try {
			//factura
			Factura factura = new Factura();
			factura.setFechaFactura(Date.from(facturaDTO.getFechaFactura().atStartOfDay(ZoneId.systemDefault()).toInstant()));	//TODO: pasar a un util
			
			Cliente cliente = new Cliente();
			cliente.setId(facturaDTO.getIdCliente());
			factura.setCliente(cliente);
			
			Mesa mesa = new Mesa();
			mesa.setId(facturaDTO.getIdMesa());
			factura.setMesa(mesa);
			
			Camarero camarero = new Camarero();
			camarero.setId(facturaDTO.getIdCamarero());
			factura.setCamarero(camarero);
			
			em.persist(factura);
			
			//detalle factura
			DetalleFactura detalleFactura;
			Cocinero cocinero;
			
			if(facturaDTO.getDetalleDTO() != null) {
				for(DetalleFacturaDTO detalle : facturaDTO.getDetalleDTO()) {
					cocinero = new Cocinero();
					cocinero.setId(detalle.getIdCocinero());
					
					detalleFactura = new DetalleFactura();
					detalleFactura.setFactura(factura);
					detalleFactura.setCocinero(cocinero);
					detalleFactura.setPlato(detalle.getPlato());
					detalleFactura.setImporte(detalle.getImporte());
					
					em.persist(detalleFactura);
				}
			}
		} catch (Exception e) {
			//TODO: manejar excepcion.
			return false;
		}
		
		return true;
	}

}
