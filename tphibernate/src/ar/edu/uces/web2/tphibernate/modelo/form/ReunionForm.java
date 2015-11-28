package ar.edu.uces.web2.tphibernate.modelo.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ar.edu.uces.web2.tphibernate.modelo.base.Invitacion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.base.UsuarioInvitado;

public class ReunionForm extends EventoForm {

	private String temario;
	private long idSala;																	//la sala elegida
	private List<Sala>salas=new ArrayList<Sala>(); 											//todas las salas posibles
	
	private int idEstado;																	//noConfirmado, aceptado o cancelado
	private String estado;																	//reunionAutor//reunionNoConfirmado//reunionConfirmada//reunionCancelada
	
	private Set<Invitacion> invitaciones=new HashSet<Invitacion>();							//invitaciones actuales solo para mostrar
	
	
	private List<String> tokensInvitadosMasConfirmacion=new ArrayList<String>();							//lista de pares idUsuario|confirmacion elegidoen el formulario//todos los usuarios posibles, y seteados los agregados //TODO: Ajax: listar solo los usuarios que no estar invitados
//	private List<Invitacion> usuariosInvitaciones=new ArrayList<Invitacion>();
	private Map<Usuario,Integer> mapaUsuariosMasConfirmacion=new TreeMap<Usuario,Integer>();			//usuario/idConfirmacion
	//private List<Integer> idsInvitados=new ArrayList<Integer>() ;							//los usuarios elegidos	en el formulario ////TODO: reemplazar por mapa invitados
	//private List<UsuarioInvitado> usuariosInvitados=new ArrayList<UsuarioInvitado>();//	//todos los usuarios posibles, y seteados los agregados //TODO: listar solo los usuarios que no estar invitados 
	
	public ReunionForm(){}

	public String getTemario() {
		return temario;
	}
	public void setTemario(String temario) {
		this.temario = temario;
	}
	public long getIdSala() {
		return idSala;
	}
	public void setIdSala(long idSala) {
		this.idSala = idSala;
	}

	public List<Sala> getSalas() {
		return salas;
	}
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public Set<Invitacion> getInvitaciones() {
		return invitaciones;
	}
	public void setInvitaciones(Set<Invitacion> invitaciones) {
		this.invitaciones = invitaciones;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<String> getTokensInvitadosMasConfirmacion() {
		return tokensInvitadosMasConfirmacion;
	}

	public void setTokensInvitadosMasConfirmacion(List<String> tokensInvitadosMasConfirmacion) {
		this.tokensInvitadosMasConfirmacion = tokensInvitadosMasConfirmacion;
	}

	public Map<Usuario, Integer> getMapaUsuariosMasConfirmacion() {
		return mapaUsuariosMasConfirmacion;
	}

	public void setMapaUsuariosMasConfirmacion(Map<Usuario, Integer> mapaUsuariosMasConfirmacion) {
		this.mapaUsuariosMasConfirmacion = mapaUsuariosMasConfirmacion;
	}
	

	
	/*
	public List<Invitacion> getUsuariosInvitaciones() {
		return usuariosInvitaciones;
	}

	public void setUsuariosInvitaciones(List<Invitacion> usuariosInvitaciones) {
		usuariosInvitaciones = usuariosInvitaciones;
	}
*/
}
