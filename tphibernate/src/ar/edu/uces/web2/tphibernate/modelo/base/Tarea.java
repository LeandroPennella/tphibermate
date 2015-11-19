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
	public String getEstado(){return "tarea";}
}
