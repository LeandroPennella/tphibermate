package ar.edu.uces.web2.tphibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.web2.tphibernate.modelo.base.Sala;

@Transactional(readOnly = true)
@Component
public class SalaDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Sala sala) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(sala);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Sala> getAll()
	{
		Session session = sessionFactory.getCurrentSession();
		Query q=session.createQuery("from " +Sala.class.getName());
		List<Sala>salas=(List<Sala>)q.list();
		return salas;
	}
}
