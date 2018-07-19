package ar.edu.uces.web2.tphibernate.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.EventoDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Evento;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {
	private EventoDAO eventoDAO;
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@RequestMapping(value = "/evento/mover",method=RequestMethod.POST, produces="text/plain")
	public @ResponseBody String   moverEvento(HttpServletRequest request, HttpServletResponse response, @RequestBody Evento evento)
	{
		return eventoDAO.mover(evento);
		 
	}
/*
	
	public int diferenciaSemanas(Date fechaDestino) {
		
		 
		Date fechaActual=new Date();
 
		int dias=(int) ((fechaActual.getTime()-fechaDestino.getTime())/86400000);
		int semanas=dias/7;
		
	}
	
	
	public int difSem(Date fecha) {
		Calendar actual = new GregorianCalendar();
        Calendar destino = new GregorianCalendar();
        
		actual.setTime(new Date());		
        destino.setTime(fecha);
        int difA = destino.get(Calendar.YEAR) - actual.get(Calendar.YEAR);
        int difM = difA * 12 + destino.get(Calendar.MONTH) - actual.get(Calendar.MONTH);
        int difS;
        if(difA==0) {
        	difS=destino.get(Calendar.WEEK_OF_YEAR)-actual.get(Calendar.WEEK_OF_YEAR);
		} else if (difA==1) {//futuro
			
		}
	}

	
	
	
	
	public long difDiasEntre2fechas(int Y1,int M1,int D1,int Y2,int M2,int D2){
		
		
		java.util.Date fecha = new Date();

		
		
		dia = Integer.toString(c.get(Calendar.DATE));
		mes = Integer.toString(c.get(Calendar.MONTH));
		annio = Integer.toString(c.get(Calendar.YEAR));
		
		java.util.GregorianCalendar date=new java.util.GregorianCalendar(Y1,M1,D1);
		java.util.GregorianCalendar date2=new java.util.GregorianCalendar(Y2,M2,D2);
		long difms=date2.getTimeInMillis() - date1.getTimeInMillis();
		long difd=difms / (1000 * 60 * 60 * 24);
		return difd;
		}
	
	public int semanasOffset(Date fecha){
		int semanasOffset=0;
		//establecer en que semana del anio que estoy
		Calendar calendar = Calendar.getInstance();
		int semanaActual=Calendar.WEEK_OF_YEAR;
		
		calendar.setTime(fecha);
		int semanaDestino=Calendar.WEEK_OF_YEAR;
		
		//establecer la semana del anio que esta la fecha
		//si es el mismo anio devolver semanaActual-semanaDestino
		//si no es el mismo anio
		return semanasOffset;
		
	}
	*/
}
