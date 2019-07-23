package org.cocina.dao.jpa;

import org.cocina.dao.MesaDAO;
import org.cocina.dao.jpa.entity.*;

import javax.ejb.Stateless;

/**
 * Implementación sobre JPA de acciones específicas a la entidad mesa.
 */
@Stateless
public class JpaMesaDAO extends JpaDAO<Mesa> implements MesaDAO {
}
