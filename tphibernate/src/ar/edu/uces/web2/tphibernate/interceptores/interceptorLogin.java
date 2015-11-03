package ar.edu.uces.web2.tphibernate.interceptores;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//intercepta la llegada a login
public class interceptorLogin implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session= request.getSession();
		/*
		//si existen cookies con usuario y contraseña coincidente
		//y se autentican contra la bd
		if(existeCookie("usuario")){
			//persistir en sesion
		}
		//si no existe en cookies
		else
		{	
		
		*/
		//si no existe el usuario en sesion
			if ((session.getAttribute("usuario")==null))
			{
				RequestDispatcher rd= request.getRequestDispatcher("/login.do");
				rd.forward(request, response);
				return false;		//no pasa por postHandle ni afterCompletion
			}
			else
			{
				RequestDispatcher rd= request.getRequestDispatcher("/agenda/mostrarCalendario.do");
				rd.forward(request, response);
				return false;		//no pasa por postHandle ni afterCompletion
			}
			
		//}	
		//return true; //sigue o no sigue // al controlador o al siguiente interceptor
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}
}
