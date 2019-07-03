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

import org.cocina.dao.jpa.Camarero;
import org.cocina.dao.jpa.Cliente;
import org.cocina.dao.jpa.Cocinero;
import org.cocina.dao.jpa.DetalleFactura;
import org.cocina.dao.jpa.Factura;
import org.cocina.dao.jpa.Mesa;
import org.cocina.dto.CamareroDTO;
import org.cocina.dto.ClienteDTO;
import org.cocina.dto.CocineroDTO;
import org.cocina.dto.ConsultaCamareroDTO;
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

	@Override
	public List<ConsultaCamareroDTO> consultaCamareroRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
		List<ConsultaCamareroDTO> lista = new ArrayList<>();
		String mes = fechaInicial.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
		
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("select c.id id_camarero, c.nombre nombre, c.primer_apellido apellido, ");
		sqlString.append("(select coalesce(sum(d.importe),0) ");
		sqlString.append("from factura f, detalle_factura d ");
		sqlString.append("where f.id = d.factura_id ");
		sqlString.append("and f.camarero_id = c.id ");
		sqlString.append("and f.fecha_factura between ?1 and ?2 ");
		sqlString.append(") sumatoriaImporte ");
		sqlString.append("from camarero c ");
		sqlString.append("order by 4 desc ");

		@SuppressWarnings("unchecked")
		List<Object[]> resultado = em.createNativeQuery(sqlString.toString(), "ResultadoFacturadoCamareroAlMes")
				.setParameter(1, Date.from(fechaInicial.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE)
				.setParameter(2, Date.from(fechaFinal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE)
				.getResultList();
		
		if(resultado != null) {
			for(Object[] o : resultado) {
				Camarero c = (Camarero)o[0];
				BigDecimal suma = (BigDecimal)o[1];
				lista.add(new ConsultaCamareroDTO.Builder(c.getId()).nombre(c.getNombre()).apellido(c.getPrimerApellido()).mes(mes).sumatoriaImporte(suma).build());
			}
		}

		return lista;
	}

}
