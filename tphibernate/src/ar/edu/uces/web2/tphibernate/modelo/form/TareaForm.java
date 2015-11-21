package ar.edu.uces.web2.tphibernate.modelo.form;

public class TareaForm extends EventoForm{
	

	private String direccion;
	private String descripcion;
	

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
