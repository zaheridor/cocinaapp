package org.cocina.ejb;

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
			factura.setFechaFactura(facturaDTO.getFechaFactura());
			
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
			
			if(facturaDTO.getDetalleFacturas() != null) {
				for(DetalleFacturaDTO detalle : facturaDTO.getDetalleFacturas()) {
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
