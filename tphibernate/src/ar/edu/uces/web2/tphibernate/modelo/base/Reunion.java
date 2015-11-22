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
	
	@Override
	public String obtenerEstado(Usuario usuario){
		String sEstado="reunion";
		if (this.getAutor().getId()==usuario.getId())
			sEstado="reunionAutor";
		else  {
			for(Invitado invitado: this.getInvitados())
			{
				if (invitado.getUsuario()==usuario)
				{
					switch(invitado.getAceptado()){
						case 0:
							sEstado="reunionNoConfirmado";
						case 1:
							sEstado="reunionConfirmada";
						default :
							sEstado="reunionCancelada";
					}		
				}
			}
		}
		return sEstado;
	}
}
