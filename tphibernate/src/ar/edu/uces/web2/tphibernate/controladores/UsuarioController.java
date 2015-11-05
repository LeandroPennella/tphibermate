package ar.edu.uces.web2.tphibernate.controladores;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioAutenticacionForm;
import ar.edu.uces.web2.tphibernate.modelo.validadores.UsuarioAutenticacionFormValidator;


@SessionAttributes("usuario")
@Controller
public class UsuarioController {


	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioAutenticacionFormValidator usuarioAutenticacionFormValidator;

	@Autowired
	public void seUsuarioDao(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@RequestMapping(value = "/autenticacion/login")
	public ModelAndView identificar(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/views/autenticacion/login.jsp","usuarioAutenticacionForm",new UsuarioAutenticacionForm());
	}
	
	@RequestMapping(value = "/autenticacion/validar")
	public ModelAndView validar(  @ModelAttribute("usuarioAutenticacionForm") UsuarioAutenticacionForm usuarioAutenticacionForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {

		//TODO:no sabe si jugador viene de sesion o de get?
		
		this.usuarioAutenticacionFormValidator.validate(usuarioAutenticacionForm, result);	
		if (result.hasErrors()) {
			return new ModelAndView("/views/autenticacion/login.jsp");
		}
		
		//autenticar
		Usuario usuario=usuarioDAO.autenticar(usuarioAutenticacionForm.getNombreUsuario(), usuarioAutenticacionForm.getContrasenia());
		if (usuario!=null) {
			asentar(usuarioAutenticacionForm,request,response,usuarioAutenticacionForm.getRecordarme());
			//todo: separar lengua_pais
			//	localeResolver.setLocale(request, response, new Locale(idioma) );
			return new ModelAndView("/views/index.jsp");//usurio y contraseña no coinciden	
	
		}
		else {
			return new ModelAndView("/views/autenticacion/login.jsp");//usurio y contraseña no coinciden	
		}
	}
	
	public void asentar(UsuarioAutenticacionForm usuarioAutenticacionForm, HttpServletRequest request, HttpServletResponse response, boolean recordarme)
	{
		
		//lo guarda en sesion
		Usuario usuario=new Usuario();
		//si esta marcado recordar, guarda una cookie
		if(recordarme)
		{
			boolean encontrado=false;
			Cookie cookie ;
			//buscar cookie
			cookie = obtenerCookie(request, "nombre");

			if (cookie!=null)
			{
				//si  existe la cookie 
				encontrado=true;
				//actualizar fecha de ultima

        			//cookie.setValue(Integer.toString(scoreActual));
        			//cookie.setMaxAge(60*60*24*365);
        			//cookie.setPath("/");
        			//response.addCookie(cookie);
        		
	        } 
			//si no  existe
			if (!encontrado)
			{
				//crearla
				cookie=new Cookie("nombre",usuario.getNombre());
				cookie.setMaxAge(60*60*24*365);
				cookie.setPath("/");
				response.addCookie(cookie);
				cookie=new Cookie("contrasenia",usuario.getContrasenia().toString());
				cookie.setMaxAge(60*60*24*365);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		else
		{
			//buscar si esta la cookie
			//si esta sacarla 
		}
	}
	
	public void logout()
	{}
	
    public Cookie obtenerCookie(HttpServletRequest request, String name) {
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
