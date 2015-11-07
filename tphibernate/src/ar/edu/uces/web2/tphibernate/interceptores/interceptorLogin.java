package ar.edu.uces.web2.tphibernate.interceptores;
//declarado en context.xml

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

//intercepta la llegada a login
public class interceptorLogin implements HandlerInterceptor {
	private UsuarioDAO usuarioDAO;
	@Autowired
	public void setUsuarioDao(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		Cookie cookieUsuario = obtenerCookie(request, "nombreUsuario");
		Cookie cookieContraseña = obtenerCookie(request, "contrasenia");
		
		HttpSession session= request.getSession();
		//si existe el usuario en sesion
		if ((session.getAttribute("usuario")!=null))
		{
			// no pasar por el login
			RequestDispatcher rd= request.getRequestDispatcher("/agenda/mostrarCalendario.do");
			rd.forward(request, response);
			return false;		
		} else if ((cookieUsuario!=null)&&(cookieContraseña!=null)) { //existe en cookies
			//validar usuario y contraseña de cookies y 
			Usuario usuario=usuarioDAO.autenticar(cookieUsuario.getValue(), cookieContraseña.getValue());
			//persistir sesion
			request.getSession().setAttribute("usuario",usuario);
			RequestDispatcher rd= request.getRequestDispatcher("/agenda/mostrarCalendario.do");
			rd.forward(request, response);
			return false;
		}

		//si no existe pasar por login
		return true; 
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}
	
	

	private Cookie obtenerCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
