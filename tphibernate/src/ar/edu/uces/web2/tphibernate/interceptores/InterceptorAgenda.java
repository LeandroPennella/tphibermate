
package ar.edu.uces.web2.tphibernate.interceptores;
//declarado en context.xml

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//intercepta la llegada al punto de inicio

public class InterceptorAgenda implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session= request.getSession();
		//si no existe el usuario en sesion
		if ((session.getAttribute("usuarioLogueado")==null))
		{
			// pasar por el login
			RequestDispatcher rd= request.getRequestDispatcher("/autenticacion/login.do");
			rd.forward(request, response);
			return false;		
		}
			
		// si existe sigue
		return true;  
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}
}
