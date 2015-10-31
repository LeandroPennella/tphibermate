package ar.edu.uces.web2.tphibernate.modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;


@Transactional(readOnly = true)
@Component
public class UsarioDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Usuario get(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.get(Usuario.class, id);// no tira excepcion si no lo encuentra	
		}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		
		//guarda el id en la instancia
		session.saveOrUpdate(usuario);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Usuario auteniticar(String nombreUsuario, String contrasenia) {
		Session session = sessionFactory.getCurrentSession();
		Usuario usuario= (Usuario)session.createQuery("from " +Usuario.class.getName()+" as u where u.nombreUsuario = '?' and u.contrasenia='?'")
				.setString(0,nombreUsuario)
				.setString(0,contrasenia)
				.uniqueResult();
		return usuario;
		
	}
}
