package ar.edu.uces.web2.tphibernate.modelo.base;

public class Usuario {
	
	private long id;
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	private Contrasenia contrasenia;
	private String idioma;
	
	public Usuario(){}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Contrasenia getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(Contrasenia contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	

}
