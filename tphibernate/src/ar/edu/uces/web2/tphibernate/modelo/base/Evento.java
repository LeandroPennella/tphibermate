	package ar.edu.uces.web2.tphibernate.modelo.base;

import java.util.Date;

public class Evento {

	private long id;

	private String titulo;
	private Date fecha;
	private String horaInicio;
	private String horaFin;

	//TODO: buscar mapeo campo calculado transient hibernate hbm.xml
	//https://www.adictosaltrabajo.com/tutoriales/eventosenhibernatei/
	//transient private int minutosDuracion;
	


	private Usuario autor;
	private Usuario usuarioActual;
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
		
	public Evento (){}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	
	public int getMinutosDuracion() {
		int minutosDuracion=1;
		return minutosDuracion;
	}
	
	
//	public void setDuracion(int duracion) {
//		this.duracion = duracion;
//	}
	
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public String obtenerEstado(Usuario usuario){return "evento";}
		
}
