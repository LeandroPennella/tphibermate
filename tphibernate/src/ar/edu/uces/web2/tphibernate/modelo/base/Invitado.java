package ar.edu.uces.web2.tphibernate.modelo.base;

public class Invitado {
	private long id;
	private Usuario usuario;
	private int aceptado;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int  getAceptado() {
		return aceptado;
	}
	public void setAceptado(int aceptado) {
		this.aceptado = aceptado;
	}
	

}
