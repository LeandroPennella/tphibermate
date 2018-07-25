package ar.edu.uces.web2.tphibernate.controladores;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import ar.edu.uces.web2.tphibernate.dao.InvitacionDAO;
import ar.edu.uces.web2.tphibernate.dao.ReunionDAO;
import ar.edu.uces.web2.tphibernate.dao.SalaDAO;
import ar.edu.uces.web2.tphibernate.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Invitacion;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.base.UsuarioInvitado;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;
import ar.edu.uces.web2.tphibernate.validadores.EventoFormValidator;
import ar.edu.uces.web2.tphibernate.validadores.ReunionFormValidator;

@SessionAttributes("usuarioLogueado") 

@Controller
public class ReunionController {
	
	private ReunionDAO reunionDAO;
	private UsuarioDAO usuarioDAO;
	private SalaDAO salaDAO;
	private InvitacionDAO invitacionDAO;
	
	private Map<String, Usuario> posiblesParticipantes;
	
	@Autowired
	public void setReunionDAO (ReunionDAO reunionDAO) {
		this.reunionDAO = reunionDAO;
	}
	
	@Autowired
	public void setUsuarioDAO (UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Autowired
	public void setSalaDAO (SalaDAO salaDAO) {
		this.salaDAO = salaDAO;
	}

	@Autowired
	public void setInvitacionDAO (InvitacionDAO invitacionDAO) {
		this.invitacionDAO = invitacionDAO;
	}
	
	@Autowired
	private ReunionFormValidator reunionValidator; 

	@Autowired
	private EventoFormValidator eventoFormValidator; 
	
	

	@RequestMapping(value = "/calendario/listadoDeUsuarios", method = RequestMethod.GET)
	public @ResponseBody List<Usuario> listadoDeUsuarios (String parteNombre, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado )
	{
		
		List<Usuario> usuarios=usuarioDAO.find(parteNombre);
		
		//saca usuarioLogueado
		for(Usuario usuario:usuarios)
		{
			if (usuario.getId()==usuarioLogueado.getId())
			{								
				usuarios.remove(usuario);
				break;
			}
		}	
		return usuarios;

	}
	
	
	
	@RequestMapping(value = "/calendario/crearReunion")
	public ModelAndView crear(@ModelAttribute("usuarioLogueado") Usuario usuarioLogueado) {
		
		ReunionForm reunionForm=new ReunionForm();
		reunionForm.setSalas(salaDAO.getAll());
		reunionForm.setAutor(usuarioLogueado);
		ModelAndView mv=new ModelAndView("/views/calendario/reunion.jsp","reunionForm", reunionForm);
		return mv;
	}
	
	@RequestMapping(value = "/calendario/editarReunion")
	public ModelAndView editar(@RequestParam("idEvento")Long idReunion, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado) {		//viene del calendario con el id del evento a modificar

		Reunion reunion=reunionDAO.get(idReunion);
		
		ReunionForm reunionForm=new ReunionForm(reunion);	
		reunionForm.setEstado(reunionForm.obtenerEstado(usuarioLogueado));
		reunionForm.setSalas(salaDAO.getAll());
		
		return  new ModelAndView("/views/calendario/reunion.jsp","reunionForm",reunionForm);
	}
	
	@RequestMapping(value = "/calendario/modificarAsistenciaReunion")
	public ModelAndView modificarAsistencia(@ModelAttribute("reunionForm") ReunionForm reunionForm, BindingResult result, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado){
	
		if (!reunionForm.getIdEvento().isEmpty())  {//TODO: ver para que es esta validacion // sumarla a la siguiente
			if (reunionForm.getIdEstado()!=0){			
				Invitacion invitacion=invitacionDAO.getByReunionUsuario(Long.parseLong(reunionForm.getIdEvento()), usuarioLogueado.getId());
				if (invitacion!=null)
				{
					invitacion.setAceptado(reunionForm.getIdEstado());
					invitacionDAO.update(invitacion);
				} //TODO: manejar error
				return new ModelAndView("/views/index.jsp");
			}
		}
		return new ModelAndView("/views/calendario/reunion.jsp","reunionForm", reunionForm);
	}
		
	@RequestMapping(value = "/calendario/agregarReunion")//TODO: modificar url
	public ModelAndView save(@ModelAttribute("reunionForm") ReunionForm reunionForm, BindingResult result, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado ) {

		this.eventoFormValidator.validate(reunionForm, result);
		this.reunionValidator.validate(reunionForm, result);
		if (result.hasErrors()) {		//tiene errores
			return new ModelAndView("/views/calendario/reunion.jsp","reunionForm", save_conError(reunionForm, usuarioLogueado));
		} else {																							//no tiene errores
			if(save_sinError(reunionForm, usuarioLogueado)==false){
				//TODO: manejarlo con excepciones?
				
				//TODO:mensaje de error - reunion eliminada por el autor
			}

			//return new ModelAndView("/views/index.jsp");
			String url="redirect:/calendario/mostrarCalendario.do?";			
			url+="anio="+reunionForm.getFecha().substring(6,10)+"&";
			url+="mes="+reunionForm.getFecha().substring(3,5)+"&";
			url+="dia="+reunionForm.getFecha().substring(0,2);						
			
			return new ModelAndView(url);
			


		}
	}
	private ReunionForm save_conError(ReunionForm reunionForm, Usuario usuarioLogueado) {
		//TODO: Agregarle salas y usuarios

		if (!reunionForm.getIdEvento().isEmpty())  { //en modificacion
			Reunion reunion=reunionDAO.get(Long.parseLong(reunionForm.getIdEvento()));
			reunionForm.setInvitaciones(reunion.getInvitaciones());
			reunionForm.setAutor(reunion.getAutor());
		} else { 																//errores en creacion
			reunionForm.setIdEvento(null);//TODO: para que???
		}
		
		reunionForm.setSalas(salaDAO.getAll());
		//TODO:agregar los idUsuariosInvitados > invitacionesPendiente  con estado pendientes 
		Set<Invitacion> invitaciones=new HashSet<Invitacion>();
		if(reunionForm.getInvitados()!=null)
		{
			for(int idUsuarioInvitado:reunionForm.getInvitados())
			{
				Invitacion invitacion=new Invitacion();
				invitacion.setUsuario(usuarioDAO.get(idUsuarioInvitado));
				invitacion.setAceptado(0);
				invitaciones.add(invitacion);
			}
		}
		reunionForm.setInvitaciones(invitaciones);
		reunionForm.setAutor(usuarioLogueado);
		return reunionForm;

	}
	private boolean save_sinError(ReunionForm reunionForm, Usuario usuarioLogueado) {
		
		Reunion reunion;
		if (!reunionForm.getIdEvento().isEmpty())  { //modificar
			reunion=reunionDAO.get(Long.parseLong(reunionForm.getIdEvento()));
		if (reunion==null) {return false;}
			//System.out.println(reunion.getId());
		}  else  {									//crear
			reunion=new Reunion();
			reunion.setAutor(usuarioLogueado);
		}

		reunion.setTitulo(reunionForm.getTitulo());
		SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		Date fecha=dateFormatter.parse(reunionForm.getFecha(), new ParsePosition(0));	
		reunion.setFecha(fecha);
		reunion.setHoraInicio(reunionForm.getHoraInicio());
		reunion.setHoraFin(reunionForm.getHoraFin());
		reunion.setTemario(reunionForm.getTemario());
		Sala sala=new Sala();
		sala.setId(reunionForm.getIdSala());
		reunion.setSala(sala);
		
		
		
		//invitaciones
		
		
		int[] idsUsuariosInvitados=reunionForm.getInvitados();
		Set<Invitacion>invitacionesAnteriores=new HashSet<Invitacion>();	//las que vienen del evento antes de editar
		Set<Invitacion>invitacionesActuales=new HashSet<Invitacion>(); 		//a guardar
		invitacionesAnteriores=reunion.getInvitaciones();
		
		
		
		//TODO: revisar cada invitado, si estaba en una invitacion anterior a la edicion, mantener el estado de la invitacion; sino aceptado=0
		
		
		if (idsUsuariosInvitados!=null)
		{
			boolean estaba;
			
			System.out.println("invtaciones anteriores:");
			for(int idUsuarioInvitado : idsUsuariosInvitados)
			{
				
				estaba=false;
				if(invitacionesAnteriores!=null)
				{
					for(Invitacion invitacion:invitacionesAnteriores)
					{
						System.out.println(invitacion.getUsuario().getNombreUsuario());
						if (invitacion.getUsuario().getId()==idUsuarioInvitado)
						{
							estaba=true;
							//mantiene el usuario invitado
							invitacionesActuales.add(invitacion);
							break;
						}
					}
				}
				
				//> si no estaban agregar,  
	
				if (!estaba || reunionForm.getIdEvento().isEmpty()) {
					
					invitacionesActuales.add(new Invitacion(idUsuarioInvitado, 0 ));
				} 
				
				//error > si estaban y no estan, se quitan al no agregarlos
				
				//TODO: ver como eliminar
				
				//System.out.println(idUsuario);
			}
		}
		System.out.println("invtaciones actuales:");
		for(Invitacion invitacionPrint:invitacionesActuales)
		{System.out.println(invitacionPrint.getUsuario().getId()+""+invitacionPrint.getAceptado());}
		
		
		//reunion.setInvitaciones(invitacionesActuales);

		if (reunion.getInvitaciones()==null){
			reunion.setInvitaciones(invitacionesActuales);
		} else {
			reunion.getInvitaciones().clear();
			reunion.getInvitaciones().addAll(invitacionesActuales);
		}

		//TODO: agrega nuevos, no modifica

		reunionDAO.save(reunion);
		return true;
	}
	
	@RequestMapping(value = "/calendario/eliminarReunion")
	public ModelAndView delete(@RequestParam("idEvento")Integer idEvento){
		Reunion reunion=reunionDAO.get(idEvento);
		reunionDAO.delete(reunion);
		
		
		String url="redirect:/calendario/mostrarCalendario.do?";			
		url+="anio="+new SimpleDateFormat("yyyy").format(reunion.getFecha())+"&";
		url+="mes="+new SimpleDateFormat("MM").format(reunion.getFecha())+"&";
		url+="dia="+new SimpleDateFormat("dd").format(reunion.getFecha());						
		
		
		
		return new ModelAndView(url);
	}

}
