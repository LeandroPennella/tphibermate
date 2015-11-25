package ar.edu.uces.web2.tphibernate.modelo.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.uces.web2.tphibernate.modelo.base.Invitacion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.UsuarioInvitado;

public class ReunionForm {
	private String idEvento;
	//TODO: extender de EventoForm
	private String Titulo;
	private String Fecha;
	private String HoraInicio;
	private String horaFin;
	private String temario;
	private long idSala;																	//la sala elegida
	private List<Sala>salas=new ArrayList<Sala>(); 											//todas las salas posibles
	
	private int idEstado;																	//noConfirmado, aceptado o cancelado
	private String estado;																	//reunionAutor//reunionNoConfirmado//reunionConfirmada//reunionCancelada
	
	private List<String> mapaInvitados=new ArrayList<String>();								//lista de pares idUsuario|estado elegidoen el formulario
	private List<Integer> idsInvitados=new ArrayList<Integer>() ;							//los usuarios elegidos	en el formulario ////TODO: reemplazar por mapa invitados
	private List<UsuarioInvitado> usuariosInvitados=new ArrayList<UsuarioInvitado>();//		//todos los usuarios posibles, y seteados los agregados //TODO: listar solo los usuarios que no estar invitados 
	
	private Set<Invitacion> invitaciones=new HashSet<Invitacion>();							//invitaciones actuales solo para mostrar
	
	public ReunionForm(){}
	
	public String getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getHoraInicio() {
		return HoraInicio;
	}
	public void setHoraInicio(String horaInicio) {
		HoraInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
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
	public List<Integer> getIdsInvitados() {
		return idsInvitados;
	}
	public void setIdsInvitados(List<Integer> idsInvitados) {
		this.idsInvitados = idsInvitados;
	}
	public List<Sala> getSalas() {
		return salas;
	}
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public List<UsuarioInvitado> getUsuariosInvitados() {
		return usuariosInvitados;
	}
	public void setUsuariosInvitados(List<UsuarioInvitado> usuariosInvitados) {
		this.usuariosInvitados = usuariosInvitados;
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
	
	public List<String> getMapaInvitados() {
		return mapaInvitados;
	}
	public void setMapaInvitados(List<String> mapaInvitados) {
		this.mapaInvitados = mapaInvitados;
	}
}
