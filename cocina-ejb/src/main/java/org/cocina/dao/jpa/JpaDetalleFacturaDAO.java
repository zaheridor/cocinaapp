package org.cocina.dao.jpa;

import org.cocina.dao.DetalleFacturaDAO;
import org.cocina.dao.jpa.entity.*;

import javax.ejb.Stateless;

/**
 * Implementación sobre JPA de acciones específicas a la entidad detalle de factura.
 */
@Stateless
public class JpaDetalleFacturaDAO extends JpaDAO<DetalleFactura> implements DetalleFacturaDAO {
}
