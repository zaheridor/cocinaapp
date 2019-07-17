package org.cocina.ejb;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Local;

import org.cocina.dto.CamareroDTO;
import org.cocina.dto.ClienteDTO;
import org.cocina.dto.CocineroDTO;
import org.cocina.dto.ConsultaBaseDTO;
import org.cocina.dto.FacturaDTO;
import org.cocina.dto.MesaDTO;
import org.cocina.excepciones.GeneralException;

@Local
public interface CocinaEJBLocal {

	public void guardarFactura(FacturaDTO factura) throws GeneralException;
	
	public List<MesaDTO> listadoMesas();
	
	public List<ClienteDTO> listadoClientes();
	
	public List<CamareroDTO> listadoCamareros();
	
	public List<CocineroDTO> listadoCocineros();
	
	public List<ConsultaBaseDTO> consultarCamarerosPorRangoFecha(LocalDate fechaInicial, LocalDate fechaFinal);
	
	public List<ConsultaBaseDTO> consultarClientesPorGastosMayoresA(BigDecimal valorMinimoGastado);
	
}
