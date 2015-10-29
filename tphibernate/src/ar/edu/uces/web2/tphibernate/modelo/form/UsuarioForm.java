package ar.edu.uces.web2.tphibernate.modelo.form;

public class UsuarioForm {
	
	private String nombreUsuario;
	private String contrasenia;
	private Boolean recordarme;

	public UsuarioForm(){}
	
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
