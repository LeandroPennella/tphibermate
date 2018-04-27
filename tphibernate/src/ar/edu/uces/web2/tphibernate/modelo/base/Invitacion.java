package ar.edu.uces.web2.tphibernate.modelo.base;

public class Invitacion {
	private long id;
	private Usuario usuario;
	private int aceptado;				//TODO: idConfirmacion
	/*
	 * 0>no confirmado
		1>confirmado
		2>cancelado
	 */
	//TODO: SolucionInvitados
	private Reunion reunion;
	public Invitacion() {}
	public Invitacion(int idUsuario, int aceptado) {
		Usuario usuario=new Usuario();
		usuario.setId(idUsuario);
		this.setUsuario(usuario);
		this.setAceptado(aceptado);
	}
	public Reunion getReunion() {
		return reunion;
	}
	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
	//TODO: SolucionInvitados
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
