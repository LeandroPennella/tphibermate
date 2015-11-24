package ar.edu.uces.web2.tphibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.web2.tphibernate.modelo.base.Invitacion;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

@Transactional(readOnly = true)
@Component
public class InvitacionDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	public Invitacion getByReunionUsuario(long idReunion, long idUsuario) {
		Session session = sessionFactory.getCurrentSession();
		
		String sQuery=
		"select i from " +Invitacion.class.getName() + " as i "+
				" where i.reunion.id = :idReunion " +
				" and i.usuario.id= :idUsuario " ;
				
		Query q=session.createQuery(sQuery);
		
		q.setLong("idReunion",idReunion);
		q.setLong("idUsuario", idUsuario);
		
		Invitacion invitacion=(Invitacion)q.uniqueResult();
		return invitacion;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Invitacion invitacion ) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(invitacion);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(Invitacion invitacion ) {
		Session session = sessionFactory.getCurrentSession();
		session.update(invitacion);
	}
}
