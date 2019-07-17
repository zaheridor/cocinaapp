package org.cocina.ejb;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.cocina.dao.jpa.Camarero;
import org.cocina.dao.jpa.Cliente;
import org.cocina.dao.jpa.Cliente_;
import org.cocina.dao.jpa.Cocinero;
import org.cocina.dao.jpa.DetalleFactura;
import org.cocina.dao.jpa.DetalleFactura_;
import org.cocina.dao.jpa.Factura;
import org.cocina.dao.jpa.Factura_;
import org.cocina.dao.jpa.Mesa;
import org.cocina.dto.CamareroDTO;
import org.cocina.dto.ClienteDTO;
import org.cocina.dto.CocineroDTO;
import org.cocina.dto.ConsultaBaseDTO;
import org.cocina.dto.DetalleFacturaDTO;
import org.cocina.dto.FacturaDTO;
import org.cocina.dto.MesaDTO;
import org.cocina.excepciones.GeneralException;
import org.cocina.util.Utilitario;

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

	@Override
	public List<ConsultaBaseDTO> consultarCamarerosPorRangoFecha(LocalDate fechaInicial, LocalDate fechaFinal) {
		List<ConsultaBaseDTO> lista = new ArrayList<>();
		String mes = fechaInicial.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
		
		String sqlString = Utilitario.obtenerSqlQueryCamareroConJoins();

		@SuppressWarnings("unchecked")
		List<Object[]> resultado = em.createNativeQuery(sqlString, "ResultadoFacturadoCamareroAlMes")
				.setParameter(1, Date.from(fechaInicial.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE)
				.setParameter(2, Date.from(fechaFinal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE)
				.getResultList();
		
		if(resultado != null) {
			for(Object[] o : resultado) {
				Camarero c = (Camarero)o[0];
				BigDecimal suma = (BigDecimal)o[1];
				lista.add(new ConsultaBaseDTO.Builder(c.getId()).nombre(c.getNombre()).apellido(c.getPrimerApellido()).mes(mes).sumatoriaImporte(suma).build());
			}
		}

		return lista;
	}

	@Override
	public List<ConsultaBaseDTO> consultarClientesPorGastosMayoresA(BigDecimal valorMinimoGastado) {
		List<ConsultaBaseDTO> lista = new ArrayList<>();
		List<Object[]> resultado = obtenerConsultaCliente(valorMinimoGastado);
		
		if(resultado != null) {
			for(Object[] o : resultado) {
				lista.add(new ConsultaBaseDTO.Builder((Integer)o[0]).nombre((String)o[1]).apellido((String)o[2]).sumatoriaImporte((BigDecimal)o[3]).build());
			}
		}
		
		return lista;
	}
	
	private List<Object[]> obtenerConsultaCliente(BigDecimal valorMinimoGastado) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<Cliente> cliente = query.from(Cliente.class);
		Join<Cliente, Factura> facturaJoin = cliente.join(Cliente_.facturas);
		Join<Factura, DetalleFactura> detalleJoin = facturaJoin.join(Factura_.detalleFacturas);
		
		
		query.multiselect(cliente.get(Cliente_.id), cliente.get(Cliente_.nombre), cliente.get(Cliente_.primerApellido), builder.sum(detalleJoin.get(DetalleFactura_.importe)));
		query.groupBy(cliente.get(Cliente_.id), cliente.get(Cliente_.nombre), cliente.get(Cliente_.primerApellido));
		query.having(builder.gt(builder.sum(detalleJoin.get(DetalleFactura_.importe)), valorMinimoGastado));
		query.orderBy(builder.asc(builder.sum(detalleJoin.get(DetalleFactura_.importe))));
		
		TypedQuery<Object[]> tipo = em.createQuery(query);
		return tipo.getResultList();
	}
	
	private List<Cliente> obtenerClientePorNombre() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
		Root<Cliente> cliente = query.from(Cliente.class);
		query.where(builder.equal(cliente.get(Cliente_.nombre), "Juan"));
		TypedQuery<Cliente> tipo = em.createQuery(query);
		
		return tipo.getResultList();
	}
	
	private List<Cliente> obtenerTodosLosClientes(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
		Root<Cliente> cliente = query.from(Cliente.class);
		TypedQuery<Cliente> tipo = em.createQuery(query);
		
		return tipo.getResultList();
	}

}
