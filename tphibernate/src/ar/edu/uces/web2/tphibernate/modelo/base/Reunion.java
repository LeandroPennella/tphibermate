package ar.edu.uces.web2.tphibernate.modelo.base;

import java.util.Set;

public class Reunion extends Evento {

	private String temario;
	private Sala sala;
	private Set<Invitado> invitados;
	
	public Reunion() {
		
	}
	public String getTemario() {
		return temario;
	}
	public void setTemario(String temario) {
		this.temario = temario;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Set<Invitado> getInvitados() {
		return invitados;
	}
	public void setInvitados(Set<Invitado> invitados) {
		this.invitados = invitados;
	}
}
