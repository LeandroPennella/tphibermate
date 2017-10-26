package ar.edu.uces.web2.tphibernate.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

@Controller
public class HorasController {
	
	@RequestMapping(value = "/horas/mostrar")
	public ModelAndView mostrar(HttpServletRequest request, HttpServletResponse response) {

		List<String> horas = new ArrayList<String>();
		horas.add("0:00");
		horas.add("0:30");
		horas.add("1:00");

		ModelAndView mv=new ModelAndView("/views/ajax/horas.jsp");
		mv.addObject("horas",horas);
		return mv;

	}
}
