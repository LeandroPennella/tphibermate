package ar.edu.uces.web2.tphibernate.controladores;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
/*
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioForm;
import ar.edu.uces.web2.tphibernate.modelo.validadores.UsuarioFormValidator;

@Controller
public class UsuarioController {


	@RequestMapping(value = "/login")
	public ModelAndView identificarJugador() {
		return new ModelAndView("/views/usuario/login.jsp","usuarioForm",new UsuarioForm());
	}
	/*
	@RequestMapping(value = "/usuario/autenticar")
	public ModelAndView validarJugador(  @ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
	
		//todo:no sabe si jugador viene de sesion o de get?
		this.jugadorValidador.validate(jugador, result);	
		if (result.hasErrors()) {
			return new ModelAndView("/views/identificarJugador.jsp","idiomas", listarIdiomas());
		}
		//todo: separar lengua_pais
		localeResolver.setLocale(request, response, new Locale(idioma) );
		return new ModelAndView("/eventos/mostrarCalendario.do","jugador",jugador);
	}
	*/
}
