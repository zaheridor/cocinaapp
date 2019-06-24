package org.cocina.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cocina.dao.jpa.Mesa;

/**
 * Session Bean implementation class CocinaEJB
 */
@Stateless
public class CocinaEJB implements CocinaEJBRemote, CocinaEJBLocal {
	
	@PersistenceContext(unitName = "cocinaPU")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public CocinaEJB() {
    }

	@Override
	public void test() {
		Mesa m = new Mesa();
		m.setNumMaxComensales(10);
		m.setUbicacion("Ventana");
		em.persist(m);
	}

}
