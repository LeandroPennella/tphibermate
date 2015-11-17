package ar.edu.uces.web2.tphibernate.modelo.form;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

public class ReunionForm {
	
	private String Titulo;
	private String Fecha;
	private String HoraInicio;
	private String horaFin;
	private String temario;
	private Integer idSala;
	
	private List<Integer> idsParticipantes=new ArrayList<Integer>() ;
	private List<Usuario> usuarios=new ArrayList<Usuario>();//usuarioDAO.getAll();

	private List<Sala>salas=new ArrayList<Sala>(); //salaDAO.getAll();
	
	public ReunionForm(){}
	
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
	public Integer getIdSala() {
		return idSala;
	}
	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}

	public List<Integer> getIdsParticipantes() {
		return idsParticipantes;
	}

	public void setIdsParticipantes(List<Integer> idsParticipantes) {
		this.idsParticipantes = idsParticipantes;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Sala> getSalas() {
		return salas;
	}
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

}
