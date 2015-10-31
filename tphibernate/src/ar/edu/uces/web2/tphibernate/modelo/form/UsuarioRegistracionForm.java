package ar.edu.uces.web2.tphibernate.modelo.form;

import java.util.LinkedHashMap;
import java.util.Map;

public class UsuarioRegistracionForm {


	private String nombreUsuario;
	private String contrasenia;


	public UsuarioRegistracionForm(){}
	
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
	public Map<String,String> listarIdiomas()
	{
		Map<String,String>idiomas= new LinkedHashMap<String,String>();
		idiomas.put("es", "Castellano");
		idiomas.put("en", "English");
		return idiomas;
	}
}
