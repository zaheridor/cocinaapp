package org.cocina.ejb;

import org.cocina.dao.*;
import org.cocina.dao.jpa.entity.*;
import org.cocina.dto.*;
import org.cocina.excepciones.GeneralException;
import org.cocina.util.Utilitario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * EJB que centraliza la lógica de negocio declarada en la interfaz.
 */
@Stateless
public class CocinaEJB implements CocinaEJBRemote, CocinaEJBLocal {

	@EJB(name = "clienteDAO")
	private ClienteDAO clienteDAO;

	@EJB(name = "camareroDAO")
	private CamareroDAO camareroDAO;

	@EJB(name = "cocineroDAO")
	private CocineroDAO cocineroDAO;

	@EJB(name = "facturaDAO")
	private FacturaDAO facturaDAO;

	@EJB(name = "detalleFacturaDAO")
	private DetalleFacturaDAO detalleFacturaDAO;

	@EJB(name = "mesaDAO")
	private MesaDAO mesaDAO;

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

			facturaDAO.guardar(factura);
			
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

					detalleFacturaDAO.guardar(detalleFactura);
				}
			}
		} catch (Exception e) {
			throw new GeneralException("Error en método guardarFactura()", e);
		}
		
	}

	@Override
	public List<MesaDTO> listadoMesas() {
		List<Mesa> mesaSet = mesaDAO.buscarTodos("Mesa.findAll", Mesa.class);
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
		List<Cliente> clienteSet = clienteDAO.buscarTodos("Cliente.findAll", Cliente.class);
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
		List<Camarero> camareroSet = camareroDAO.buscarTodos("Camarero.findAll", Camarero.class);
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
		List<Cocinero> cocineroSet = cocineroDAO.buscarTodos("Cocinero.findAll", Cocinero.class);
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
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultado = camareroDAO.obtenerConsultaCamarero(Utilitario.obtenerSqlQueryCamareroConJoins(),
				"ResultadoFacturadoCamareroAlMes",
				Date.from(fechaInicial.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
				Date.from(fechaFinal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
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
		List<Object[]> resultado = clienteDAO.obtenerConsultaCliente(valorMinimoGastado);
		
		if(resultado != null) {
			for(Object[] o : resultado) {
				lista.add(new ConsultaBaseDTO.Builder((Integer)o[0]).nombre((String)o[1]).apellido((String)o[2]).sumatoriaImporte((BigDecimal)o[3]).build());
			}
		}
		
		return lista;
	}

}
