package ar.edu.uces.web2.tphibernate.modelo.base;

import java.util.List;

public class Reunion extends Evento {

	private String temario;
	private Sala sala;
	private List<Usuario> participantes;
	
	public Reunion() {
		super();
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
	public List<Usuario> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}
}
