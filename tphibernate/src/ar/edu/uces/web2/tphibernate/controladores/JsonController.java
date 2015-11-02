package ar.edu.uces.web2.tphibernate.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.uces.web2.tphibernate.modelo.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;

@Controller
public class JsonController {
	private UsuarioDAO usuarioDAO;

	@Autowired
	public void setSomethingDao(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@RequestMapping(value = "/get/{id}")
	public @ResponseBody Map<String, Usuario> get(@PathVariable long id) {
		System.out.println("Get Something with id " + id);
		Map<String, Usuario> out = new HashMap<String, Usuario>();
		out.put("usuario", usuarioDAO.get(id));
		return out;
	}

	@RequestMapping(value = "/find")
	public @ResponseBody Map<String, Usuario> find(@RequestParam("id") long id) {
		System.out.println("Find usuario with id " + id);
		Map<String, Usuario> out = new HashMap<String, Usuario>();
		out.put("usuario", usuarioDAO.get(id));
		return out;
	}

	@RequestMapping(value = "/find2")
	public @ResponseBody String find2(@RequestParam("id") long id) {
		System.out.println("Find usuario with id " + id);
		Map<String, Usuario> out = new HashMap<String, Usuario>();
		out.put("usuario", usuarioDAO.get(id));
		// return out;
		return "/views/test.jsp";
	}

	@RequestMapping(value = "/createSomethingAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Usuario> createSomething(@RequestBody Usuario usuario) {
		//System.out.println("Creatin something with value "+ usuario.getValue());
		usuarioDAO.save(usuario);
		Map<String, Usuario> out = new HashMap<String, Usuario>();
		out.put("something", usuario);
		return out;

	}
}
