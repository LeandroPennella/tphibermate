package ar.edu.uces.web2.tphibernate.modelo.base;

public class UsuarioInvitado{
	private Usuario usuario;
	private boolean agregado;
	
	public UsuarioInvitado(){}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isAgregado() {
		return agregado;
	}
	public void setAgregado(boolean agregado) {
		this.agregado = agregado;
	}
	
	
	
}