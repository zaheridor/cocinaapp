package org.cocina.dao.jpa;

import org.cocina.dao.CocineroDAO;
import org.cocina.dao.jpa.entity.*;

import javax.ejb.Stateless;

/**
 * Implementación sobre JPA de acciones específicas a la entidad cocinero.
 */
@Stateless
public class JpaCocineroDAO extends JpaDAO<Cocinero> implements CocineroDAO {
}
