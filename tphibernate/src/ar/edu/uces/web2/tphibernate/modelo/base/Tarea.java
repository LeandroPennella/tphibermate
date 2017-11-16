package ar.edu.uces.web2.tphibernate.modelo.base;

public class Tarea extends Evento {

	private String descripcion;
	private String direccion;
	
	public Tarea(){}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String obtenerEstado(Usuario usuario){return "tarea";}
	/*
	@Override
	public int obtenerMinutosDuracion() {

	
		
		return 1;//hora1enMinutos-hora2enMinutos;
		
	}*/
}
