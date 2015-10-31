package ar.edu.uces.web2.tphibernate.modelo.form;

import java.util.LinkedHashMap;
import java.util.Map;

public class UsuarioAutenticacionForm {
	
	private String nombreUsuario;
	private String contrasenia;
	private Boolean recordarme;

	public UsuarioAutenticacionForm(){}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public Boolean getRecordarme() {
		return recordarme;
	}
	public void setRecordarme(Boolean recordarme) {
		this.recordarme = recordarme;
	}
	

}
