package ar.edu.uces.web2.tphibernate.modelo.base;

public class Reunion extends Evento {

	private String temario;
	private Sala sala;
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
	
}
