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

/**
 * Contrato de las acciones soportadas por la lógica de negocio.
 */
@Local
public interface CocinaEJBLocal {

	void guardarFactura(FacturaDTO factura) throws GeneralException;
	
	List<MesaDTO> listadoMesas();
	
	List<ClienteDTO> listadoClientes();
	
	List<CamareroDTO> listadoCamareros();
	
	List<CocineroDTO> listadoCocineros();
	
	List<ConsultaBaseDTO> consultarCamarerosPorRangoFecha(LocalDate fechaInicial, LocalDate fechaFinal);
	
	List<ConsultaBaseDTO> consultarClientesPorGastosMayoresA(BigDecimal valorMinimoGastado);
	
}
