package ar.edu.uces.web2.tphibernate.modelo.base;

public class UsuarioInvitado{
	private Usuario usuario;
	private int estado;	//-1 no invitado // 0 // 1 // 2 
	
	public UsuarioInvitado(){}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}