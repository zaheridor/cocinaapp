package org.cocina.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.cocina.ejb.CocinaEJBLocal;
import javax.faces.annotation.FacesConfig;

/**
 * Debido a un error en Glassfish 5, se deben dejar las anotaciones @ApplicationScoped y @FacesConfig.
 * @author zaheridor
 *
 */
@Named
@ApplicationScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class CocinaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private CocinaEJBLocal ejb;
	
	public CocinaBean() {
	}
	
	public String prueba() {
		ejb.test();
		return null;
	}
}
