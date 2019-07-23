package org.cocina.dao;

import org.cocina.dao.jpa.entity.Camarero;

import java.util.Date;
import java.util.List;

/**
 * Contrato de las acciones especificas de base de datos para la entidad camarero.
 */
public interface CamareroDAO extends CommonDAO<Camarero> {

    List<Object[]> obtenerConsultaCamarero(String queryNativo, String mapeador, Date fechaInicial, Date fechaFinal);

}
