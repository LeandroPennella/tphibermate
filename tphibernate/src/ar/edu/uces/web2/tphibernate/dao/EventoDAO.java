package ar.edu.uces.web2.tphibernate.dao;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import ar.edu.uces.web2.tphibernate.modelo.base.Evento;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

@Transactional(readOnly = true)
@Component
public class EventoDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Evento> getByAutorAndDate(Usuario usuario, Date fecha )
	{
		Session session = sessionFactory.getCurrentSession();
		//Query q=session.createQuery("from " +Evento.class.getName() + " as e where e.fecha=?");
		String sQuery=
		"select distinct e from " +Evento.class.getName() + " as e " +
				"left join fetch e.invitaciones as i " +
				"where (e.autor.id = :idUsuario or i.usuario.id=:idUsuario) " +
				"and e.fecha= :fecha " +
				"order by e.horaInicio";
		Query q=session.createQuery(sQuery);
		q.setDate("fecha",fecha);
		q.setLong("idUsuario", usuario.getId());
		
		List<Evento>eventos=(List<Evento>)q.list();
//		eventos.sort(new EventoPorHoraComparator());
		return eventos;
	}
	@Transactional(readOnly = false)
	public String mover(Evento evento)
	{
		
		Session session = sessionFactory.getCurrentSession();

		// para mantener la duracion
		mantenerDuracion((Evento)session.get(Evento.class, evento.getId()), evento);
		
		String sQuery=
		"update from " +Evento.class.getName() + " as e " +
			"set horaInicio='"+evento.getHoraInicio()+"', "+
				"horaFin='"+evento.getHoraFin()+"' "+
			"where e.id="+evento.getId();
		Query q=session.createQuery(sQuery);
		q.executeUpdate();
		/*
		Evento eventoBuffer=(Evento)session.get(Evento.class, evento.getId());
		eventoBuffer.setHoraInicio(evento.getHoraInicio());
		session.saveOrUpdate(eventoBuffer);*/
		return evento.getHoraFin();
	}
	
	
	private void mantenerDuracion(Evento eventoOriginal, Evento eventoModificado)
	{

		
		//obtener diferencia
		//https://stackoverflow.com/questions/5351483/calculate-date-time-difference-in-java

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
		Date dHoraInicioOriginal = null;
		Date dHoraInicioNueva = null;
		Date dHoraFinOriginal=null;
		Date dHoraFinNueva=null;
		try {
			dHoraInicioNueva = formatter.parse(eventoModificado.getHoraInicio());
			dHoraInicioOriginal = formatter.parse(eventoOriginal.getHoraInicio());
			dHoraFinOriginal=formatter.parse(eventoOriginal.getHoraFin());
		
		} catch (ParseException | java.text.ParseException e) {
		    e.printStackTrace();
		} 
		
		int dDiferenciaHoraFin=(int)((dHoraInicioNueva.getTime()-dHoraInicioOriginal.getTime())/60000);
		
		
		//modificar horaFin
		//http://developando.com/blog/java-sumar-restar-horas-dias-fecha
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dHoraFinOriginal); 
		calendar.add(Calendar.MINUTE, dDiferenciaHoraFin);  
		dHoraFinNueva=calendar.getTime();
		eventoModificado.setHoraFin(formatter.format(dHoraFinNueva));
		
		
	}
	
	
	
	//TODO: mover?
	private class EventoPorHoraComparator implements Comparator<Evento> {
	    @Override
	    public int compare(Evento e1, Evento e2) {
			SimpleDateFormat horaFormat=new SimpleDateFormat("HH:mm");
	    	Date inicio1=horaFormat.parse(e1.getHoraInicio(), new ParsePosition(0));
	    	Date inicio2=horaFormat.parse(e2.getHoraInicio(), new ParsePosition(0));
	        return inicio1.compareTo(inicio2);
	    }


	}
}
