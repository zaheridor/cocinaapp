package org.cocina.dao;

import org.cocina.dao.jpa.entity.Cliente;

import java.math.BigDecimal;
import java.util.List;

/**
 * Contrato de las acciones de base de datos específicas para la entidad cliente.
 */
public interface ClienteDAO extends CommonDAO<Cliente> {

    List<Object[]> obtenerConsultaCliente(BigDecimal valorMinimoGastado);

    List<Cliente> obtenerClientePorNombre(String nombre);

    List<Cliente> obtenerTodosLosClientes();

}
