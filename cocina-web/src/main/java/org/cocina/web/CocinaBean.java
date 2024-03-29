package org.cocina.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.cocina.dto.CamareroDTO;
import org.cocina.dto.ClienteDTO;
import org.cocina.dto.CocineroDTO;
import org.cocina.dto.DetalleFacturaDTO;
import org.cocina.dto.FacturaDTO;
import org.cocina.dto.MesaDTO;
import org.cocina.ejb.CocinaEJBLocal;
import org.cocina.excepciones.GeneralException;
import org.cocina.web.vo.DetalleFacturaVO;
import org.cocina.web.vo.FacturaVO;

/**
 * Controlador encargado de procesar las peticiones del front y comunicarse con la lògica de negocio.
 * @author zaheridor
 *
 */
@Named
@SessionScoped
public class CocinaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private FacturaVO factura;
	private List<DetalleFacturaVO> detalleSet;
	private List<MesaDTO> mesaSet;
	private List<ClienteDTO> clienteSet;
	private List<CamareroDTO> camareroSet;
	private List<CocineroDTO> cocineroSet;

	@EJB
	private CocinaEJBLocal ejb;
	
	public CocinaBean() {
	}
	
	@PostConstruct
	public void init() {
		detalleSet = new ArrayList<>();
		detalleSet.add(new DetalleFacturaVO());
		factura = new FacturaVO();
	}

	/**
	 * Permite agregar registros al detalle de la factura.
	 */
	public void adicionarDetalle() {
		detalleSet.add(new DetalleFacturaVO());
	}

	/**
	 * Permite remover registros de un detalle de la factura.
	 * @param detalle
	 */
	public void removerDetalle(DetalleFacturaVO detalle) {
		detalleSet.remove(detalle);
	}

	/**
	 * Método inicial para crear una factura.
	 * @return
	 */
	public String irCrearFactura() {
		mesaSet = ejb.listadoMesas();
		clienteSet = ejb.listadoClientes();
		camareroSet = ejb.listadoCamareros();
		cocineroSet = ejb.listadoCocineros();

		return "crearFactura";
	}

	/**
	 * Método inicial para la consulta de clientes.
	 * @return
	 */
	public String consultaCliente() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("consultaCliente", ejb.consultarClientesPorGastosMayoresA(new BigDecimal(10_000)));
		
		return "consultaCliente";
	}

	/**
	 * Método inicial para la consulta de camareros.
	 * @return
	 */
	public String consultaCamarero() {
		LocalDate fechaInicial = LocalDate.now().withDayOfMonth(1);
		LocalDate fechaFinal = LocalDate.now().withDayOfMonth(fechaInicial.lengthOfMonth());
		
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("consultaCamarero", ejb.consultarCamarerosPorRangoFecha(fechaInicial, fechaFinal));

		return "consultaCamarero";
	}

	/**
	 * Procesa la acción de guardar una factura diligenciada por el cliente.
	 * @return
	 */
	public String guardarFactura() {
		FacturaDTO.Builder builder = new FacturaDTO.Builder().cliente(factura.getIdCliente()).mesa(factura.getIdMesa()).camarero(factura.getIdCamarero()).fechaFactura(new Date());
		
		for(DetalleFacturaVO vo : this.detalleSet){
			builder.adicionarDetalle(new DetalleFacturaDTO.Builder().cocinero(vo.getIdCocinero()).plato(vo.getPlato()).importe(vo.getImporte()).build());
		}
		
		FacturaDTO factura = builder.build();
		
		try {
			ejb.guardarFactura(factura);
		} catch (GeneralException ex) {
			return "error";
		}
		
		return "resultado";
	}

	public List<MesaDTO> getMesaSet() {
		return mesaSet;
	}

	public void setMesaSet(List<MesaDTO> mesaSet) {
		this.mesaSet = mesaSet;
	}

	public List<ClienteDTO> getClienteSet() {
		return clienteSet;
	}

	public void setClienteSet(List<ClienteDTO> clienteSet) {
		this.clienteSet = clienteSet;
	}

	public List<CamareroDTO> getCamareroSet() {
		return camareroSet;
	}

	public void setCamareroSet(List<CamareroDTO> camareroSet) {
		this.camareroSet = camareroSet;
	}

	public List<CocineroDTO> getCocineroSet() {
		return cocineroSet;
	}

	public void setCocineroSet(List<CocineroDTO> cocineroSet) {
		this.cocineroSet = cocineroSet;
	}

	public FacturaVO getFactura() {
		return factura;
	}

	public void setFactura(FacturaVO factura) {
		this.factura = factura;
	}

	public List<DetalleFacturaVO> getDetalleSet() {
		return detalleSet;
	}

	public void setDetalleSet(List<DetalleFacturaVO> detalleSet) {
		this.detalleSet = detalleSet;
	}
}
