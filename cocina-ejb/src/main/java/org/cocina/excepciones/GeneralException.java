package org.cocina.excepciones;

/**
 * Excepción general de la aplicación.
 * Excepción general de la aplicación.
 */
public class GeneralException extends Exception {

	public GeneralException() {
		
	}
	
	public GeneralException(String mensage) {
		super(mensage);
	}
	
	public GeneralException(String mensage, Throwable causa) {
		super(mensage, causa);
	}
	
}
