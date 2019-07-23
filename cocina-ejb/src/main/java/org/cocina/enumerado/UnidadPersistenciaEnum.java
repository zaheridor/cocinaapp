package org.cocina.enumerado;

public enum UnidadPersistenciaEnum {
    COCINA_PU("cocinaPU");

    UnidadPersistenciaEnum(String nombre){
        this.nombre = nombre;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }
}
