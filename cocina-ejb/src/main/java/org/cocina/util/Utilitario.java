package org.cocina.util;

public class Utilitario {

	@Deprecated
	public static String obtenerSqlQueryCamareroSinJoins() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("select c.id id_camarero, c.nombre nombre, c.primer_apellido apellido, ");
		sqlString.append("(select coalesce(sum(d.importe),0) ");
		sqlString.append("from factura f, detalle_factura d ");
		sqlString.append("where f.id = d.factura_id ");
		sqlString.append("and f.camarero_id = c.id ");
		sqlString.append("and f.fecha_factura between ?1 and ?2 ");
		sqlString.append(") sumatoriaImporte ");
		sqlString.append("from camarero c ");
		sqlString.append("order by 4 desc ");
		
		return sqlString.toString();
	}
	
	public static String obtenerSqlQueryCamareroConJoins() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("select c.id id_camarero, c.nombre nombre, c.primer_apellido apellido, coalesce(sum(d.importe),0) sumatoriaImporte ");
		sqlString.append("from camarero c ");
		sqlString.append("left outer join factura f on f.camarero_id = c.id and f.fecha_factura between ?1 and ?2 ");
		sqlString.append("left outer join detalle_factura d on f.id = d.factura_id ");
		sqlString.append("group by c.id, c.nombre, c.primer_apellido ");
		sqlString.append("order by 4 desc ");
		
		return sqlString.toString();
	}
	
}
