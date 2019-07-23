package org.cocina.dao.jpa;

import org.cocina.dao.CamareroDAO;
import org.cocina.dao.jpa.entity.*;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Implementación sobre JPA de acciones específicas a la entidad camarero.
 */
@Stateless
public class JpaCamareroDAO extends JpaDAO<Camarero> implements CamareroDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> obtenerConsultaCamarero(String queryNativo, String mapeador, Date fechaInicial, Date fechaFinal){
        return getEntityManager().createNativeQuery(queryNativo, mapeador)
                .setParameter(1, fechaInicial, TemporalType.DATE)
                .setParameter(2, fechaFinal, TemporalType.DATE)
                .getResultList();
    }

}
