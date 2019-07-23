package org.cocina.dao.jpa;

import org.cocina.dao.CommonDAO;
import org.cocina.enumerado.UnidadPersistenciaEnum;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Clase base encargada de manejar la persistencia con la base de datos mediante JPA.
 * @param <E>
 */
public abstract class JpaDAO<E> implements CommonDAO<E> {

    private EntityManagerFactory emf;
    private EntityManager em;

    /**
     * Retorna un entity manager de la fábrica de persistencia.
     * @return
     */
    protected EntityManager getEntityManager(){
        emf = Persistence.createEntityManagerFactory(UnidadPersistenciaEnum.COCINA_PU.getNombre());
        em = emf.createEntityManager();

        return em;
    }

    /**
     * Cierra la conexión con el entity manager.
     */
    public void cerrarSesion(){
        if(getEntityManager().isOpen()){
            getEntityManager().close();
        }
    }

    /**
     * Método genérico para guardar en base de datos.
     * @param o
     */
    public void guardar(E o){
        getEntityManager().persist(o);
    }

    /**
     * Método genérico para obtener todos los registros de una entidad específica.
     * @param consulta
     * @param clase
     * @return
     */
    public List<E> buscarTodos(String consulta, Class<E> clase){
        return getEntityManager().createNamedQuery(consulta, clase).getResultList();
    }

}
