package ar.edu.uces.web2.tphibernate.controladores;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.TareaDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Tarea;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.form.TareaForm;
import ar.edu.uces.web2.tphibernate.validadores.EventoFormValidator;

@SessionAttributes("usuarioLogueado") 

@Controller
public class TareaController {
	private TareaDAO tareaDAO;
	
	@Autowired
	public void setTareaDAO (TareaDAO tareaDAO) {
		this.tareaDAO = tareaDAO;
	}
	@Autowired
	private EventoFormValidator eventoFormValidator; 
	
	@RequestMapping(value = "/calendario/crearTarea")
	public ModelAndView crear() {
		return new ModelAndView("/views/calendario/tarea.jsp", "tarea", new TareaForm());
	}
	
	@RequestMapping(value = "/calendario/editarTarea")
	public ModelAndView editar(@RequestParam("idEvento")Long idTarea) {
		Tarea tarea=tareaDAO.get(idTarea);
		TareaForm tareaForm=new TareaForm(); //todo: constructor conversor de tarea
		tareaForm.setIdEvento(Long.toString(tarea.getId()));
		tareaForm.setTitulo(tarea.getTitulo());
		
		SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		String sFecha=dateFormatter.format(tarea.getFecha());
		tareaForm.setFecha(sFecha);
		tareaForm.setHoraInicio(tarea.getHoraInicio());		
		tareaForm.setHoraFin(tarea.getHoraFin());
		tareaForm.setDescripcion(tarea.getDescripcion());
		tareaForm.setDireccion(tarea.getDireccion());
		return new ModelAndView("/views/calendario/tarea.jsp","tarea",tareaForm); 
	}
	
	@RequestMapping(value = "/calendario/agregarTarea")
	public ModelAndView save(@ModelAttribute("tarea") TareaForm tareaForm, BindingResult result, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado) {
		
		this.eventoFormValidator.validate(tareaForm, result);
		if (result.hasErrors()) {
			return new ModelAndView("/views/calendario/tarea.jsp","tarea", tareaForm);
		}
		SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		Date fecha=dateFormatter.parse(tareaForm.getFecha().toString(), new ParsePosition(0));	

		//tarea.setFecha(fecha);
		Tarea tarea=new Tarea();
		if (!tareaForm.getIdEvento().isEmpty())
		{tarea.setId(Long.parseLong(tareaForm.getIdEvento()));}
		tarea.setTitulo(tareaForm.getTitulo());
		tarea.setFecha(fecha);
		tarea.setHoraInicio(tareaForm.getHoraInicio());
		tarea.setHoraFin(tareaForm.getHoraFin());
		tarea.setDescripcion(tareaForm.getDescripcion());
		tarea.setDireccion(tareaForm.getDireccion());
		
		tarea.setAutor(usuarioLogueado);
		
		tareaDAO.save(tarea);
		//return new ModelAndView("/views/index.jsp");
		String url="redirect:/calendario/mostrarCalendario.do?";			
		url+="anio="+tareaForm.getFecha().substring(6,10)+"&";
		url+="mes="+tareaForm.getFecha().substring(3,5)+"&";
		url+="dia="+tareaForm.getFecha().substring(0,2);						
		
		return new ModelAndView(url);
	}
	
	@RequestMapping(value = "/calendario/eliminarTarea")
	public ModelAndView delete(@RequestParam("idEvento")Integer idEvento){
		Tarea tarea=tareaDAO.get(idEvento);
		tareaDAO.delete(tarea);
		
		//return new ModelAndView("/views/index.jsp");
		
		String url="redirect:/calendario/mostrarCalendario.do?";			
		url+="anio="+new SimpleDateFormat("yyyy").format(tarea.getFecha())+"&";
		url+="mes="+new SimpleDateFormat("MM").format(tarea.getFecha())+"&";
		url+="dia="+new SimpleDateFormat("dd").format(tarea.getFecha());						
		
		
		
		return new ModelAndView(url);
	}
	
}
