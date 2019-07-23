package org.cocina.dao.jpa;

import org.cocina.dao.ClienteDAO;
import org.cocina.dao.jpa.entity.*;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

/**
 * Implementación sobre JPA de acciones específicas a la entidad cliente.
 */
@Stateless
public class JpaClienteDAO extends JpaDAO<Cliente> implements ClienteDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> obtenerConsultaCliente(BigDecimal valorMinimoGastado) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Cliente> cliente = query.from(Cliente.class);
        Join<Cliente, Factura> facturaJoin = cliente.join(Cliente_.facturas);
        Join<Factura, DetalleFactura> detalleJoin = facturaJoin.join(Factura_.detalleFacturas);


        query.multiselect(cliente.get(Cliente_.id), cliente.get(Cliente_.nombre), cliente.get(Cliente_.primerApellido), builder.sum(detalleJoin.get(DetalleFactura_.importe)));
        query.groupBy(cliente.get(Cliente_.id), cliente.get(Cliente_.nombre), cliente.get(Cliente_.primerApellido));
        query.having(builder.gt(builder.sum(detalleJoin.get(DetalleFactura_.importe)), valorMinimoGastado));
        query.orderBy(builder.asc(builder.sum(detalleJoin.get(DetalleFactura_.importe))));

        TypedQuery<Object[]> tipo = getEntityManager().createQuery(query);
        return tipo.getResultList();
    }

    @Override
    public List<Cliente> obtenerClientePorNombre(String nombre) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);
        query.where(builder.equal(cliente.get(Cliente_.nombre), nombre));
        TypedQuery<Cliente> tipo = getEntityManager().createQuery(query);

        return tipo.getResultList();
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
        TypedQuery<Cliente> tipo = getEntityManager().createQuery(query);

        return tipo.getResultList();
    }

}
