package org.cocina.ejb;

import javax.ejb.Local;

import org.cocina.dto.FacturaDTO;

@Local
public interface CocinaEJBLocal {

	//TODO: borrar método de prueba.
	public void test();
	
	public boolean guardarFactura(FacturaDTO factura);
}
