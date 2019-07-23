package org.cocina.dao;

import java.util.List;

/**
 * Contrato que define las acciones comunes en la interacción con la base de datos para cualquier entidad.
 * @param <E>
 */
public interface CommonDAO<E> {

    void guardar(E o);

    List<E> buscarTodos(String consulta, Class<E> clase);

}
