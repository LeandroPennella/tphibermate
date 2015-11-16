package ar.edu.uces.web2.tphibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;

@Transactional(readOnly = true)
@Component
public class ReunionDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Reunion reunion) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(reunion);
	}
}
