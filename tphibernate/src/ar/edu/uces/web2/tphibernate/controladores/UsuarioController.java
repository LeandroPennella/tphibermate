package ar.edu.uces.web2.tphibernate.controladores;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioForm;


@SessionAttributes("usuario")
@Controller
public class UsuarioController {



	
	@RequestMapping(value = "/autenticacion/login")
	public ModelAndView identificar(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/views/usuario/login.jsp","usuarioForm",new UsuarioForm());
	}
	
	@RequestMapping(value = "/autenticacion/validar")
	public ModelAndView validar(  @ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
	/*
		//todo:no sabe si jugador viene de sesion o de get?
		this.jugadorValidador.validate(jugador, result);	
		if (result.hasErrors()) {
			return new ModelAndView("/views/identificarJugador.jsp","idiomas", listarIdiomas());
		}
		*/
		//todo: separar lengua_pais
	//	localeResolver.setLocale(request, response, new Locale(idioma) );
		
		asentar(usuarioForm);
		return new ModelAndView("index.jsp");//lo manda al inicio para que lo capture el interceptor
	}
	
	
	public void asentar(UsuarioForm usuarioForm)
	{
		//lo guarda en sesion
		Usuario usuario=new Usuario();
		//si esta marcado recordar, guarda una cookie
		//Cookie cookie ;

	}
	
}
