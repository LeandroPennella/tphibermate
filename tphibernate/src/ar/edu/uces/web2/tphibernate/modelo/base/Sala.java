package ar.edu.uces.web2.tphibernate.modelo.base;

public class Sala {

	private long id;
	private String descripcion;
	
	public Sala() {
	
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
