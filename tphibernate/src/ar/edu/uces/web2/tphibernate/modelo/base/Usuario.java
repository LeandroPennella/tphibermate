package ar.edu.uces.web2.tphibernate.modelo.base;

public class Usuario {
	
	private int id;
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	private Contrasenia contrasenia;
	private String lenguaje;
	
	public Usuario(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	

}
