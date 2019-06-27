package org.cocina.ejb;

import java.util.ArrayList;
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
import org.cocina.dto.CamareroDTO;
import org.cocina.dto.ClienteDTO;
import org.cocina.dto.CocineroDTO;
import org.cocina.dto.DetalleFacturaDTO;
import org.cocina.dto.FacturaDTO;
import org.cocina.dto.MesaDTO;
import org.cocina.excepciones.GeneralException;

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

	@Override
	public void guardarFactura(FacturaDTO facturaDTO) throws GeneralException {
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
			throw new GeneralException("Error en método guardarFactura()", e);
		}
		
	}

	@Override
	public List<MesaDTO> listadoMesas() {
		List<Mesa> mesaSet = em.createNamedQuery("Mesa.findAll", Mesa.class).getResultList();
		List<MesaDTO> dtoSet = new ArrayList<>();
		
		if(mesaSet != null) {
			for(Mesa mesa : mesaSet) {
				dtoSet.add(new MesaDTO.Builder(mesa.getId()).numMaxComensales(mesa.getNumMaxComensales()).ubicacion(mesa.getUbicacion()).build());
			}
		}
		
		return dtoSet;
	}

	@Override
	public List<ClienteDTO> listadoClientes() {
		List<Cliente> clienteSet = em.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
		List<ClienteDTO> dtoSet = new ArrayList<>();
		
		if(clienteSet != null) {
			for(Cliente cli : clienteSet) {
				dtoSet.add(new ClienteDTO.Builder(cli.getId()).nombre(cli.getNombre()).primerApellido(cli.getPrimerApellido()).segundoApellido(cli.getSegundoApellido()).observaciones(cli.getObservaciones()).build());
			}
		}
		
		return dtoSet;
	}

	@Override
	public List<CamareroDTO> listadoCamareros() {
		List<Camarero> camareroSet = em.createNamedQuery("Camarero.findAll", Camarero.class).getResultList();
		List<CamareroDTO> dtoSet = new ArrayList<>();
		
		if(camareroSet != null) {
			for(Camarero cam : camareroSet) {
				dtoSet.add(new CamareroDTO.Builder(cam.getId()).nombre(cam.getNombre()).primerApellido(cam.getPrimerApellido()).segundoApellido(cam.getSegundoApellido()).build());
			}
		}
		
		return dtoSet;
	}

	@Override
	public List<CocineroDTO> listadoCocineros() {
		List<Cocinero> cocineroSet = em.createNamedQuery("Cocinero.findAll", Cocinero.class).getResultList();
		List<CocineroDTO> dtoSet = new ArrayList<>();
		
		if(cocineroSet != null) {
			for(Cocinero coc : cocineroSet) {
				dtoSet.add(new CocineroDTO.Builder(coc.getId()).nombre(coc.getNombre()).primerApellido(coc.getPrimerApellido()).segundoApellido(coc.getSegundoApellido()).build());
			}
			
		}
		
		return dtoSet;
	}

}
