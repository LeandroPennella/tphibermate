package ar.edu.uces.web2.tphibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.web2.tphibernate.modelo.base.Evento;

@Transactional(readOnly = true)
@Component
public class EventoDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Evento> getAll()
	{
		Session session = sessionFactory.getCurrentSession();
		Query q=session.createQuery("from " +Evento.class.getName());
		List<Evento>usuarios=(List<Evento>)q.list();
		return usuarios;
	}
}
