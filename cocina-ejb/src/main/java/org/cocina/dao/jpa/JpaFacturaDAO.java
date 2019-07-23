package org.cocina.dao.jpa;

import org.cocina.dao.FacturaDAO;
import org.cocina.dao.jpa.entity.*;

import javax.ejb.Stateless;

/**
 * Implementación sobre JPA de acciones específicas a la entidad factura.
 */
@Stateless
public class JpaFacturaDAO extends JpaDAO<Factura> implements FacturaDAO {
}
