package ar.edu.uces.web2.tphibernate.modelo.base;

import java.util.Set;

public class Reunion extends Evento {

	private String temario;
	private Sala sala;
	private Set<Invitacion> invitaciones ;
	
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
	public Set<Invitacion> getInvitaciones() {
		return invitaciones;
	}
	public void setInvitaciones(Set<Invitacion> invitacion) {
		this.invitaciones = invitacion;
	}
	
	@Override
	public String obtenerEstado(Usuario usuario){
		String sEstado="reunion";
		if (this.getAutor().getId()==usuario.getId())
			sEstado="reunionAutor";
		else  {
			for(Invitacion invitacion: this.getInvitaciones())
			{
				if (invitacion.getUsuario().getId()==usuario.getId())
				{
					switch(invitacion.getAceptado()){
						case 0:
							sEstado="reunionNoConfirmado";
							break;
						case 1:
							sEstado="reunionConfirmada";
							break;
						default :
							sEstado="reunionCancelada";
							break;
					}		
				}
			}
		}
		return sEstado;
	}
}
