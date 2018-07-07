package ar.edu.uces.web2.tphibernate.modelo.form;

import ar.edu.uces.web2.tphibernate.modelo.base.Evento;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

public class TareaForm extends EventoForm{
	
	
	private String direccion;
	private String descripcion;
	public TareaForm() {}
	public TareaForm(Evento evento){super (evento);}

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
	
	@Override
	public String obtenerEstado(Usuario usuario){return "tarea";}
		
}
