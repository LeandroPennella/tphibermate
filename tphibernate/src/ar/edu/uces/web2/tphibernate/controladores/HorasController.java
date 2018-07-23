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
	//TODO: CalendarioController?
	@RequestMapping(value = "/horas/mostrar")
	public ModelAndView mostrar(HttpServletRequest request, HttpServletResponse response) {

		List<String> horas = new ArrayList<String>();
		horas.add("0:00");
		horas.add("0:30");
		horas.add("1:00");
		horas.add("1:30");
		horas.add("2:00");
		horas.add("2:30");
		horas.add("3:00");
		horas.add("3:30");
		horas.add("4:00");
		horas.add("4:30");
		horas.add("5:00");
		horas.add("5:30");
		horas.add("6:00");
		horas.add("6:30");
		horas.add("7:00");
		horas.add("7:30");
		horas.add("8:00");
		horas.add("8:30");
		horas.add("9:00");
		horas.add("9:30");
		horas.add("10:00");
		horas.add("10:30");
		horas.add("11:00");
		horas.add("11:30");
		horas.add("12:00");
		horas.add("12:30");
		horas.add("13:00");
		horas.add("13:30");
		horas.add("14:00");
		horas.add("14:30");
		horas.add("15:00");
		horas.add("15:30");
		horas.add("16:00");
		horas.add("16:30");
		horas.add("17:00");
		horas.add("17:30");
		horas.add("18:00");
		horas.add("18:30");
		horas.add("19:00");
		horas.add("19:30");
		horas.add("20:00");
		horas.add("20:30");
		horas.add("21:00");
		horas.add("21:30");
		horas.add("22:00");
		horas.add("22:30");
		horas.add("23:00");
		horas.add("23:30");

		ModelAndView mv=new ModelAndView("/views/ajax/horas.jsp");
		mv.addObject("horas",horas);
		return mv;

	}
	
}
